package com.andersontaraujo.keepmygaming.games.controllers;

import com.andersontaraujo.keepmygaming.games.models.Game;
import com.andersontaraujo.keepmygaming.games.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/games")
public class GameController {

    private GameRepository gameRepository;

    @Autowired
    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping
    public List<Game> retrieveAllGames() {
        return gameRepository.findAll();
    }

    @PostMapping
    public Game insertGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    @GetMapping("/{id}")
    public Game findGameById(@PathVariable String id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game with [" + id + "] NOT FOUND."));
    }

    @PutMapping("/{id}")
    public Game updateGame(@PathVariable String id, @RequestBody Game game) {
        return gameRepository.findById(id)
                .map(g -> gameRepository.save(game))
                .orElseThrow(() -> new RuntimeException("Game with [" + id + "] NOT FOUND."));
    }
}
