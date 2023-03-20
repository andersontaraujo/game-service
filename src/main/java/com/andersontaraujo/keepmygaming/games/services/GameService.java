package com.andersontaraujo.keepmygaming.games.services;

import com.andersontaraujo.keepmygaming.games.dtos.CreateGameRequestDTO;
import com.andersontaraujo.keepmygaming.games.dtos.CreateMultipleGamesRequestDTO;
import com.andersontaraujo.keepmygaming.games.dtos.GameResponseDTO;
import com.andersontaraujo.keepmygaming.games.dtos.SearchGamesResponseDTO;
import com.andersontaraujo.keepmygaming.games.dtos.UpdateGameRequestDTO;
import com.andersontaraujo.keepmygaming.games.exceptions.GameNotFoundException;
import com.andersontaraujo.keepmygaming.games.models.Game;
import com.andersontaraujo.keepmygaming.games.repositories.GameRepository;
import com.andersontaraujo.keepmygaming.games.repositories.GameResponseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.andersontaraujo.keepmygaming.games.exceptions.ErrorMessage.GAME_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class GameService {

    private final ModelMapper modelMapper;

    private final GameRepository gameRepository;

    private final GameResponseRepository gameResponseRepository;

    public List<GameResponseDTO> retrieveAllGames() {

        List<GameResponseDTO> games = new ArrayList<>();

        Iterable<GameResponseDTO> cachedGames = gameResponseRepository.findAll();

        if (!cachedGames.iterator().hasNext()) {
            games.addAll(gameRepository.findAll().stream()
                    .map(game -> modelMapper.map(game, GameResponseDTO.class))
                    .collect(Collectors.toList()));

            gameResponseRepository.saveAll(games);

            return games;
        }

        cachedGames.forEach(games::add);

        return games;
    }

    public SearchGamesResponseDTO searchGames(String name, String publisherName, Integer yearOfRelease, int page, int size, String sortByField, String sortByDirection) {

        Page<Game> games = gameRepository.searchGames(name, publisherName, yearOfRelease, page, size, sortByField, sortByDirection);

        return composeSearchResponse(games);
    }

    private SearchGamesResponseDTO composeSearchResponse(Page<Game> games) {

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
        Game savedGame = gameRepository.save(modelMapper.map(createGameRequest, Game.class));
        return modelMapper.map(savedGame, GameResponseDTO.class);
    }

    public void createMultipleGames(CreateMultipleGamesRequestDTO request) {
        List<Game> gamesToSave = request.getGames().stream()
                .map(game -> modelMapper.map(game, Game.class))
                .collect(Collectors.toList());
        gameRepository.saveAll(gamesToSave);
    }

    public GameResponseDTO findGameById(String id) {
        return gameRepository.findById(id)
                .map(game -> modelMapper.map(game, GameResponseDTO.class))
                .orElseThrow(() -> new GameNotFoundException(GAME_NOT_FOUND_MESSAGE, id));
    }

    public GameResponseDTO updateGame(String id, UpdateGameRequestDTO updateGameRequest) {
        return gameRepository.findById(id)
                .map(game -> {
                    Game gameUpdated = gameRepository.save(updateGameWithNewInfo(game, updateGameRequest));
                    return modelMapper.map(gameUpdated, GameResponseDTO.class);
                })
                .orElseThrow(() -> new GameNotFoundException(GAME_NOT_FOUND_MESSAGE, id));
    }

    private Game updateGameWithNewInfo(Game game, UpdateGameRequestDTO updateGameRequest) {
        return game.toBuilder()
                .name(updateGameRequest.getName())
                .publisherName(updateGameRequest.getPublisherName())
                .developerName(updateGameRequest.getDeveloperName())
                .platforms(updateGameRequest.getPlatforms())
                .build();
    }

    public void deleteGame(String id) {
        Game gameToDelete = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(GAME_NOT_FOUND_MESSAGE, id));
        gameRepository.delete(gameToDelete);
    }
}
