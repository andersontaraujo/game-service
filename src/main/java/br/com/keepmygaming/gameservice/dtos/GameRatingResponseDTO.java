package br.com.keepmygaming.gameservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GameRatingResponseDTO {
    private String gameId;
    private Double averageScore;
}
