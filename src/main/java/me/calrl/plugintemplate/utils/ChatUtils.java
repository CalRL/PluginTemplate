package me.calrl.plugintemplate.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.calrl.plugintemplate.PluginTemplate;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/** Utility methods for chat formatting and centering. */
public class ChatUtils {

  /** Translates hex color codes in a message (&#123;rrggbb&#125; format).
   *
   * @param message the message with hex color codes
   * @return the colorized message
   */
  public static String translateHexColorCodes(String message) {
    message = ChatColor.translateAlternateColorCodes('&', message);

    Pattern hexPattern = Pattern.compile("<#[a-fA-F0-9]{6}>");
    Matcher matcher = hexPattern.matcher(message);
    StringBuilder builder = new StringBuilder();
    while (matcher.find()) {
      String hexColor = matcher.group();
      String chatColor =
          ChatColor.of(hexColor.substring(1, hexColor.length() - 1)).toString();
      matcher.appendReplacement(builder, chatColor);
    }
    matcher.appendTail(builder);
    return builder.toString();
  }

  /** Centers a message and translates hex color codes.
   *
   * @param message the message to center
   * @return the centered and colorized message
   */
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

  /** Processes a message for a player, applying color codes.
   *
   * @param player the target player
   * @param message the message to process
   * @return the processed message
   */
  public static String processMessage(@NotNull Player player, String message) {
    // uncomment this if you use placeholder api
    // if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
    //   message = PlaceholderAPI.setPlaceholders(player, message);
    // }
    message = translateHexColorCodes(message);
    return message;
  }

  /** Prepends the plugin's prefix to a message and processes it.
   *
   * @param plugin the plugin instance
   * @param player the target player
   * @param message the message
   * @return the prefixed and processed message
   */
  public static String prefixMessage(PluginTemplate plugin, Player player, String message) {

    FileConfiguration config = plugin.getConfig();
    String prefix = config.getString("prefix");
    message = prefix + " " + message;

    message = processMessage(player, message);
    return message;
  }

  /** Repeats a string a given number of times.
   *
   * @param string the string to repeat
   * @param count the number of repetitions
   * @return the repeated string
   */
  public static String repeat(String string, int count) {
    return new String(new char[count]).replace("\0", string);
  }

  /** Centers a message in chat using pixel-width calculations.
   *
   * @param message the message to center
   * @return the centered message
   */
  public static String center(String message) {
    if (message == null || message.isEmpty()) {
      return "";
    }
    message = ChatColor.translateAlternateColorCodes('&', message);

    int messagePxSize = 0;
    boolean previousCode = false;
    boolean isBold = false;

    for (char c : message.toCharArray()) {
      if (c == '§') {
        previousCode = true;
      } else if (previousCode) {
        previousCode = false;
        isBold = c == 'l' || c == 'L';
      } else {
        DefaultFontInfo fontInfo = DefaultFontInfo.getDefaultFontInfo(c);
        messagePxSize += isBold ? fontInfo.getBoldLength() : fontInfo.getLength();
        messagePxSize++;
      }
    }
    int centerPx = 154;
    int halvedMessageSize = messagePxSize / 2;
    int toCompensate = centerPx - halvedMessageSize;
    int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
    int compensated = 0;
    StringBuilder sb = new StringBuilder();
    while (compensated < toCompensate) {
      sb.append(" ");
      compensated += spaceLength;
    }
    return sb + message;
  }
}
