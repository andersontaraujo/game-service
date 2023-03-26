package br.com.keepmygaming.gameservice.repositories;

import br.com.keepmygaming.gameservice.models.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GameRepository extends MongoRepository<Game, String>, GameCustomRepository {
    Optional<Game> findByName(String name);
}
