package me.calrl.pluginTemplate.storage;

import java.util.UUID;

public class PlayerData {
    private final UUID uuid;
    private final String name;


    public PlayerData(
            UUID uuid,
            String name
    ) {
        this.uuid = uuid;
        this.name = name;
    }
}
