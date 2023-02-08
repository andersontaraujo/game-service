package com.andersontaraujo.keepmygaming.games.repositories;

import com.andersontaraujo.keepmygaming.games.models.Game;
import org.springframework.data.domain.Page;

public interface GameCustomRepository {
    Page<Game> searchGames(String name, String publisherName, Integer yearOfRelease, int page, int size, String sortByField, String sortByDirection);
}
