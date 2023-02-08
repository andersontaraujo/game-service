package com.andersontaraujo.keepmygaming.games.services;

import com.andersontaraujo.keepmygaming.games.dtos.CreateGameRequestDTO;
import com.andersontaraujo.keepmygaming.games.dtos.GameResponseDTO;
import com.andersontaraujo.keepmygaming.games.dtos.SearchGamesResponseDTO;
import com.andersontaraujo.keepmygaming.games.dtos.UpdateGameRequestDTO;
import com.andersontaraujo.keepmygaming.games.exceptions.GameNotFoundException;
import com.andersontaraujo.keepmygaming.games.models.Game;
import com.andersontaraujo.keepmygaming.games.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.andersontaraujo.keepmygaming.games.exceptions.ErrorMessage.GAME_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class GameService {

    private final ModelMapper modelMapper;

    private final GameRepository gameRepository;

    public List<GameResponseDTO> retrieveAllGames() {
        return gameRepository.findAll().stream()
                .map(game -> modelMapper.map(game, GameResponseDTO.class))
                .collect(Collectors.toList());
    }

    public SearchGamesResponseDTO searchGames(String name, String publisherName, Integer yearOfRelease, int page, int size, String sortByField, String sortByDirection) {

        Page<Game> games = gameRepository.searchGames(name, publisherName, yearOfRelease, page, size, sortByField, sortByDirection);

        List<GameResponseDTO> content = games.getContent().stream()
                .map(game -> modelMapper.map(game, GameResponseDTO.class))
                .collect(Collectors.toList());

        return SearchGamesResponseDTO.builder()
                .currentPage(games.getNumber()+1)
                .currentSize(games.getSize())
                .totalOfItems(games.getTotalElements())
                .totalOfPages(games.getTotalPages())
                .content(content)
                .build();
    }

    public GameResponseDTO createGame(CreateGameRequestDTO createGameRequest) {
        Game gameToCreate = modelMapper.map(createGameRequest, Game.class);
        Game savedGame = gameRepository.save(gameToCreate);
        return modelMapper.map(savedGame, GameResponseDTO.class);
    }

    public GameResponseDTO findGameById(String id) {
        return gameRepository.findById(id)
                .map(g -> modelMapper.map(g, GameResponseDTO.class))
                .orElseThrow(() -> new GameNotFoundException(GAME_NOT_FOUND_MESSAGE, id));
    }

    public GameResponseDTO updateGame(String id, UpdateGameRequestDTO updateGameRequest) {
        return gameRepository.findById(id)
                .map(g -> {
                    Game gameToUpdate = modelMapper.map(updateGameRequest, Game.class);
                    Game gameUpdated = gameRepository.save(gameToUpdate);
                    return modelMapper.map(gameUpdated, GameResponseDTO.class);
                })
                .orElseThrow(() -> new GameNotFoundException(GAME_NOT_FOUND_MESSAGE, id));
    }

    public void deleteGame(String id) {
        Game gameToDelete = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(GAME_NOT_FOUND_MESSAGE, id));
        gameRepository.delete(gameToDelete);
    }
}
