package br.com.keepmygaming.gameservice.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }
}
