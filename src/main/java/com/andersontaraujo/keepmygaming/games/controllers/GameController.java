package com.andersontaraujo.keepmygaming.games.controllers;

import com.andersontaraujo.keepmygaming.games.dtos.CreateGameRequestDTO;
import com.andersontaraujo.keepmygaming.games.dtos.GameResponseDTO;
import com.andersontaraujo.keepmygaming.games.dtos.UpdateGameRequestDTO;
import com.andersontaraujo.keepmygaming.games.models.Game;
import com.andersontaraujo.keepmygaming.games.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/games")
public class GameController {

    private ModelMapper modelMapper;

    private GameRepository gameRepository;

    @Autowired
    public GameController(ModelMapper modelMapper, GameRepository gameRepository) {
        this.modelMapper = modelMapper;
        this.gameRepository = gameRepository;
    }

    @GetMapping
    public List<GameResponseDTO> retrieveAllGames() {
        return gameRepository.findAll().stream()
                .map(game -> modelMapper.map(game, GameResponseDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public GameResponseDTO createGame(@Valid @RequestBody CreateGameRequestDTO createGameRequest) {
        Game gameToCreate = modelMapper.map(createGameRequest, Game.class);
        Game savedGame = gameRepository.save(gameToCreate);
        return modelMapper.map(savedGame, GameResponseDTO.class);
    }

    @GetMapping("/{id}")
    public GameResponseDTO findGameById(@PathVariable String id) {
        return gameRepository.findById(id)
                .map(g -> modelMapper.map(g, GameResponseDTO.class))
                .orElseThrow(() -> new RuntimeException("Game with [" + id + "] NOT FOUND."));
    }

    @PutMapping("/{id}")
    public GameResponseDTO updateGame(@PathVariable String id, @Valid @RequestBody UpdateGameRequestDTO updateGameRequest) {
        return gameRepository.findById(id)
                .map(g -> {
                    Game gameToUpdate = modelMapper.map(updateGameRequest, Game.class);
                    Game gameUpdated = gameRepository.save(gameToUpdate);
                    return modelMapper.map(gameUpdated, GameResponseDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("Game with [" + id + "] NOT FOUND."));
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable String id) {
        Game gameToDelete = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game with [" + id + "] NOT FOUND."));
        gameRepository.delete(gameToDelete);
    }
}
