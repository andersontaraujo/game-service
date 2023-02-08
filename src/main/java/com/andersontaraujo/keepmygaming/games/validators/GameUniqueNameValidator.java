package com.andersontaraujo.keepmygaming.games.validators;

import com.andersontaraujo.keepmygaming.games.models.Game;
import com.andersontaraujo.keepmygaming.games.repositories.GameRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

import static com.andersontaraujo.keepmygaming.games.exceptions.ErrorMessage.GAME_WITH_DUPLICATED_NAME_MESSAGE;

@RequiredArgsConstructor
public class GameUniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    private final GameRepository gameRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<Game> game = gameRepository.findByName(value);
        if (game.isPresent()) {
            context.buildConstraintViolationWithTemplate(String.format(GAME_WITH_DUPLICATED_NAME_MESSAGE, value)).addConstraintViolation();
            return false;
        }
        return true;
    }
}
