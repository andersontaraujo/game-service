package com.andersontaraujo.keepmygaming.games.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }
}
