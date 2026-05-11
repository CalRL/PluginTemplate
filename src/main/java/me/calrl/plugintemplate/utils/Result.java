package me.calrl.plugintemplate.utils;

/** Enumeration of possible command execution results. */
public enum Result {
  SUCCESS,
  FAILURE,
  INVALID_ARGS,
  NO_PERMISSION,
  USAGE_PRINTED,
  PLAYER_ONLY,
  CONSOLE_ONLY,
  ALREADY_EXISTS,
  NOTHING_TO_DO,
  NO_CHILD,
  NOT_FOUND;

  /** Converts a boolean to a Result.
   *
   * @param bool the boolean value
   * @return {@code SUCCESS} if true, {@code FAILURE} otherwise
   */
  public static Result from(boolean bool) {
    if (bool) {
      return Result.SUCCESS;
    } else {
      return Result.FAILURE;
    }
  }
}
