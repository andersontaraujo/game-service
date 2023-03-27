package br.com.keepmygaming.gameservice.proxies;

import br.com.keepmygaming.gameservice.clients.GameOperationsServiceClient;
import br.com.keepmygaming.gameservice.dtos.GameRatingResponseDTO;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class GameOperationsServiceProxy {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final GameOperationsServiceClient gameOperationsServiceClient;

    public GameRatingResponseDTO retrieveGameRating(String gameId) {
        try {
            ResponseEntity<GameRatingResponseDTO> response = gameOperationsServiceClient.retrieveGameRating(gameId);
            logger.info("retrieveGameRating - Input: gameId: {} - Output: response: {} ", gameId, response.getBody());
            return response.getBody();
        } catch (FeignException ex) {
            logger.error("Error while trying to retrieve game rating due to {}", ex.getMessage());
        }
        return null;
    }
}
