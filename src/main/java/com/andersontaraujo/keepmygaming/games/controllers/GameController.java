package com.andersontaraujo.keepmygaming.games.controllers;

import com.andersontaraujo.keepmygaming.games.dtos.CreateGameRequestDTO;
import com.andersontaraujo.keepmygaming.games.dtos.GameResponseDTO;
import com.andersontaraujo.keepmygaming.games.dtos.UpdateGameRequestDTO;
import com.andersontaraujo.keepmygaming.games.services.GameService;
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

@RestController
@RequestMapping("/v1/games")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<GameResponseDTO> searchGames() {
        return gameService.searchGames();
    }

    @PostMapping
    public GameResponseDTO createGame(@Valid @RequestBody CreateGameRequestDTO createGameRequest) {
        return gameService.createGame(createGameRequest);
    }

    @GetMapping("/{id}")
    public GameResponseDTO findGameById(@PathVariable String id) {
        return gameService.findGameById(id);
    }

    @PutMapping("/{id}")
    public GameResponseDTO updateGame(@PathVariable String id, @Valid @RequestBody UpdateGameRequestDTO updateGameRequest) {
        return gameService.updateGame(id, updateGameRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable String id) {
        gameService.deleteGame(id);
    }
}
