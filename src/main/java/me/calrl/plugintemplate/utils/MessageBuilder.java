package me.calrl.plugintemplate.utils;

import java.util.Objects;
import me.calrl.plugintemplate.PluginTemplate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/** Builder for constructing and sending messages to players. */
public class MessageBuilder {

  private Player player;
  private PluginTemplate plugin;
  private String key;
  private String content;
  private boolean usePrefix = true;

  /** Default constructor. */
  public MessageBuilder() {

  }

  /** Constructs a MessageBuilder with a plugin reference.
   *
   * @param plugin the plugin instance
   */
  public MessageBuilder(@NotNull PluginTemplate plugin) {
    this.plugin = plugin;
  }

  /** Constructs a MessageBuilder with a plugin and player.
   *
   * @param plugin the plugin instance
   * @param player the target player
   */
  public MessageBuilder(@NotNull PluginTemplate plugin, @NotNull Player player) {
    this.player = player;
    this.plugin = plugin;
  }

  /** Sets the message content.
   *
   * @param message the message text
   * @return this builder
   */
  public MessageBuilder setMessage(@NotNull String message) {
    if (message != null) {
      this.content = message;
    }
    return this;
  }

  /** Sets the target player.
   *
   * @param player the target player
   * @return this builder
   */
  public MessageBuilder setPlayer(@NotNull Player player) {
    this.player = player;
    return this;
  }

  /** Replaces text in the message content.
   *
   * @param oldChar the text to replace
   * @param newChar the replacement text
   * @return this builder
   */
  public MessageBuilder replace(@NotNull String oldChar, @NotNull String newChar) {
    if (content.contains(oldChar)) {

      this.content = content.replace(oldChar, newChar);
    }
    return this;
  }

  /** Builds the final message string.
   *
   * @return the built message
   */
  public String build() {
    if (this.content.isEmpty() || this.content.isBlank()) {
      new DebugMode(plugin).info("MessageBuilder > Content is empty... Returning.");
      return "";
    }
    String finalMsg = this.content;
    if (this.usePrefix) {
      finalMsg = ChatUtils.prefixMessage(plugin, player, finalMsg);
    }

    finalMsg = ChatUtils.processMessage(player, finalMsg);
    if (finalMsg.contains("nomessage")) {
      new DebugMode(plugin).info("MessageBuilder > \"nomessage\" found");
      return "";
    }
    new DebugMode(plugin).info(String.format("MessageBuilder > Content: %s", content));
    return finalMsg;
  }

  /** Builds the message and sends it to the player or console.
   *
   * @return {@code Result.SUCCESS}
   */
  public Result send() {
    String message = this.build();
    Objects.requireNonNullElseGet(this.player, Bukkit::getConsoleSender).sendMessage(message);

    return Result.SUCCESS;
  }
}
