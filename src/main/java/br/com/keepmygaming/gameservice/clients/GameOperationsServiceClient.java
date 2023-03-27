package br.com.keepmygaming.gameservice.clients;

import br.com.keepmygaming.gameservice.dtos.GameRatingResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gameOperationsServiceClient", url = "${keepmygaming.game-operations-service.host}")
public interface GameOperationsServiceClient {

    @GetMapping("/v1/ratings/games/{gameId}")
    ResponseEntity<GameRatingResponseDTO> retrieveGameRating(@PathVariable String gameId);
}
