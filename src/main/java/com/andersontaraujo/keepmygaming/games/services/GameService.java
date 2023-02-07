package com.andersontaraujo.keepmygaming.games.services;

import com.andersontaraujo.keepmygaming.games.dtos.CreateGameRequestDTO;
import com.andersontaraujo.keepmygaming.games.dtos.GameResponseDTO;
import com.andersontaraujo.keepmygaming.games.dtos.UpdateGameRequestDTO;
import com.andersontaraujo.keepmygaming.games.exceptions.GameNotFoundException;
import com.andersontaraujo.keepmygaming.games.models.Game;
import com.andersontaraujo.keepmygaming.games.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.andersontaraujo.keepmygaming.games.exceptions.ErrorMessage.GAME_NOT_FOUND_MESSAGE;

@Service
public class GameService {

    private ModelMapper modelMapper;

    private GameRepository gameRepository;

    public GameService(ModelMapper modelMapper, GameRepository gameRepository) {
        this.modelMapper = modelMapper;
        this.gameRepository = gameRepository;
    }

    public List<GameResponseDTO> searchGames() {
        return gameRepository.findAll().stream()
                .map(game -> modelMapper.map(game, GameResponseDTO.class))
                .collect(Collectors.toList());
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
