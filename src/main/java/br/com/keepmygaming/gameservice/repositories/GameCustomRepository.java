package br.com.keepmygaming.gameservice.repositories;

import br.com.keepmygaming.gameservice.models.Game;
import org.springframework.data.domain.Page;

public interface GameCustomRepository {
    Page<Game> searchGames(String name, String publisherName, Integer yearOfRelease, int page, int size, String sortByField, String sortByDirection);
}
