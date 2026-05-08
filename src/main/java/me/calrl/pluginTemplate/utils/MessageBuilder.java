package me.calrl.pluginTemplate.utils;

import me.calrl.pluginTemplate.PluginTemplate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MessageBuilder {

    private Player player;
    private PluginTemplate plugin;
    private String key;
    private String content;
    private boolean usePrefix = true;

    public MessageBuilder() {

    }

    public MessageBuilder(@NotNull PluginTemplate plugin) {
        this.plugin = plugin;
    }

    public MessageBuilder(@NotNull PluginTemplate plugin, @NotNull Player player) {
        this.player = player;
        this.plugin = plugin;
    }

    public MessageBuilder setMessage(@NotNull String message) {
        if(message != null) this.content = message;
        return this;
    }

    public MessageBuilder setPlayer(@NotNull Player player) {
        this.player = player;
        return this;
    }

    public MessageBuilder replace(@NotNull String oldChar, @NotNull String newChar) {
        if(content.contains(oldChar)) {

            this.content = content.replace(oldChar, newChar);
        }
        return this;
    }

    public String build() {
        if(this.content.isEmpty() || this.content.isBlank()) {
            new DebugMode(plugin).info("MessageBuilder > Content is empty... Returning.");
            return "";
        }
        String finalMsg = this.content;
        if(this.usePrefix) {
            finalMsg = ChatUtils.prefixMessage(plugin, player, finalMsg);
        }

        finalMsg = ChatUtils.processMessage(player, finalMsg);
        if(finalMsg.contains("nomessage")) {
            new DebugMode(plugin).info("MessageBuilder > \"nomessage\" found");
            return "";
        }
        new DebugMode(plugin).info(String.format("MessageBuilder > Content: %s", content));
        return finalMsg;
    }

    public Result send() {
        String message = this.build();
        Objects.requireNonNullElseGet(this.player, Bukkit::getConsoleSender).sendMessage(message);

        return Result.SUCCESS;
    }
}