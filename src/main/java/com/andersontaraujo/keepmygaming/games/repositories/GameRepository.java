package com.andersontaraujo.keepmygaming.games.repositories;

import com.andersontaraujo.keepmygaming.games.models.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {
}
