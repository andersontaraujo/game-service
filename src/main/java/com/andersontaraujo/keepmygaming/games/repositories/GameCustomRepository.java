package com.andersontaraujo.keepmygaming.games.repositories;

import com.andersontaraujo.keepmygaming.games.models.Game;
import org.springframework.data.domain.Page;

public interface GameCustomRepository {
    Page<Game> searchGames(String name, Integer yearOfRelease, int page, int size, String sortBy, String sortByDirection);
}
