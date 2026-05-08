package me.calrl.pluginTemplate.utils;

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

    public static Result from(boolean bool) {
        if(bool) {
            return Result.SUCCESS;
        } else {
            return Result.FAILURE;
        }
    }
}