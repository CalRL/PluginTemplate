package me.calrl.plugintemplate.storage;

import java.util.UUID;

/** Holds data for a player. */
public class PlayerData {

  private final UUID uuid;
  private final String name;

  /** Constructs a PlayerData instance.
   *
   * @param uuid the player's UUID
   * @param name the player's name
   */
  public PlayerData(
      UUID uuid,
      String name
  ) {
    this.uuid = uuid;
    this.name = name;
  }
}
