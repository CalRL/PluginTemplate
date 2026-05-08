package me.calrl.pluginTemplate.utils;

public enum Permissions {
    ;

    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }
    public final String getPermission() {
        return "hubbly." + this.permission;
    }
    public final String get() { return "CHANGE_THIS." + this.permission; }
}
