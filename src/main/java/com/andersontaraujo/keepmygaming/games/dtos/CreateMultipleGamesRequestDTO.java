package com.andersontaraujo.keepmygaming.games.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CreateMultipleGamesRequestDTO {
    @NotEmpty(message = "The field 'games' must have at least one value")
    private List<CreateGameRequestDTO> games;
}
