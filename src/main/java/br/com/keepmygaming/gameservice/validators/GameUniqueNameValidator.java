package br.com.keepmygaming.gameservice.validators;

import br.com.keepmygaming.gameservice.repositories.GameRepository;
import br.com.keepmygaming.gameservice.models.Game;
import br.com.keepmygaming.gameservice.exceptions.ErrorMessage;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@RequiredArgsConstructor
public class GameUniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    private final GameRepository gameRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<Game> game = gameRepository.findByName(value);
        if (game.isPresent()) {
            context.buildConstraintViolationWithTemplate(String.format(ErrorMessage.GAME_WITH_DUPLICATED_NAME_MESSAGE, value)).addConstraintViolation();
            return false;
        }
        return true;
    }
}
