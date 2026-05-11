package me.calrl.plugintemplate.utils;

/** Enumeration of permission nodes. */
public enum Permissions {
  ;

  private final String permission;

  Permissions(String permission) {
    this.permission = permission;
  }

  /** Returns the full permission string prefixed with "hubbly.".
   *
   * @return the permission string
   */
  public final String getPermission() {
    return "hubbly." + this.permission;
  }

  /** Returns the permission string, using a placeholder prefix.
   *
   * @return the permission string
   */
  public final String get() {
    return "CHANGE_THIS." + this.permission;
  }
}
