package me.calrl.pluginTemplate.utils;

import me.calrl.pluginTemplate.PluginTemplate;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatUtils {

    public static String translateHexColorCodes(String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);

        Pattern hexPattern = Pattern.compile( "<#[a-fA-F0-9]{6}>");
        Matcher matcher = hexPattern.matcher(message);
        StringBuilder builder = new StringBuilder();
        while(matcher.find()) {
            String hexColor = matcher.group();
            String chatColor = ChatColor.of(hexColor.substring(1, hexColor.length()-1)).toString();
            matcher.appendReplacement(builder, chatColor);
        }
        matcher.appendTail(builder);
        return builder.toString();
    }

    public static String centerMessage(String message) {
        message = translateHexColorCodes(message);
        message = translateCenterMessage(message);

        return message;
    }

    private static String translateCenterMessage(String message) {
        Pattern centerPattern = Pattern.compile("<center>(.*?)</center>");
        Matcher matcher = centerPattern.matcher(message);
        StringBuilder buffer = new StringBuilder();
        while (matcher.find()) {
            String centeredText = matcher.group(1);
            String centeredMessage = center(centeredText);
            matcher.appendReplacement(buffer, centeredMessage);
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    public static String processMessage(@NotNull Player player, String message) {
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            message = PlaceholderAPI.setPlaceholders(player, message);
        }
        message = translateHexColorCodes(message);
        return message;
    }

    public static String prefixMessage(PluginTemplate plugin, Player player, String message) {

        FileConfiguration config = plugin.getConfig();
        String prefix = config.getString("prefix");
        message = prefix + " " + message;

        message = processMessage(player, message);
        return message;
    }

    public static String repeat(String string, int count) {
        return new String(new char[count]).replace("\0", string);
    }

    public static String center(String message) {
        if(message == null || message.isEmpty()) {
            return "";
        }
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
            }else if(previousCode){
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            }else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }
        int centerPx = 154;
        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = centerPx - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        return sb + message;
    }
}
