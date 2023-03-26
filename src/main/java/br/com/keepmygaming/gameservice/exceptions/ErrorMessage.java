package br.com.keepmygaming.gameservice.exceptions;

public final class ErrorMessage {

    private ErrorMessage() {}

    public static final String GAME_NOT_FOUND_MESSAGE = "Could not find any game with id [%s].";
    public static final String GAME_WITH_DUPLICATED_NAME_MESSAGE = "A game with name [%s] already exists.";
}
