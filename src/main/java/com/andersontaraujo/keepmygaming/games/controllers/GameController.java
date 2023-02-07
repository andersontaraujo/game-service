package com.andersontaraujo.keepmygaming.games.controllers;

import com.andersontaraujo.keepmygaming.games.dtos.CreateGameRequestDTO;
import com.andersontaraujo.keepmygaming.games.dtos.GameResponseDTO;
import com.andersontaraujo.keepmygaming.games.dtos.SearchGamesResponseDTO;
import com.andersontaraujo.keepmygaming.games.dtos.UpdateGameRequestDTO;
import com.andersontaraujo.keepmygaming.games.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/games")
public class GameController {

    private final GameService gameService;

    @GetMapping("/all")
    public ResponseEntity<List<GameResponseDTO>> retrieveAllGames() {
        List<GameResponseDTO> games = gameService.retrieveAllGames();
        return ResponseEntity.ok(games);
    }

    @GetMapping
    public ResponseEntity<SearchGamesResponseDTO> searchGames(@RequestParam(required = false) String name,
                                                              @RequestParam(required = false) Integer yearOfRelease,
                                                              @RequestParam int page,
                                                              @RequestParam int size,
                                                              @RequestParam String sortBy,
                                                              @RequestParam String sortByDirection) {
        SearchGamesResponseDTO response = gameService.searchGames(name, yearOfRelease, page, size, sortBy, sortByDirection);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<GameResponseDTO> createGame(@Valid @RequestBody CreateGameRequestDTO createGameRequest) {
        GameResponseDTO game = gameService.createGame(createGameRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(game.getId()).toUri();
        return ResponseEntity.created(location).body(game);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDTO> findGameById(@PathVariable String id) {
        GameResponseDTO game = gameService.findGameById(id);
        return ResponseEntity.ok(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponseDTO> updateGame(@PathVariable String id, @Valid @RequestBody UpdateGameRequestDTO updateGameRequest) {
        GameResponseDTO game = gameService.updateGame(id, updateGameRequest);
        return ResponseEntity.ok(game);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable String id) {
        gameService.deleteGame(id);
        return ResponseEntity.ok().build();
    }
}
