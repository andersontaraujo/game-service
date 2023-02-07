package com.andersontaraujo.keepmygaming.games.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchGamesResponseDTO {
    private Integer currentPage;
    private Integer currentSize;
    private Long totalOfItems;
    private Integer totalOfPages;
    private List<GameResponseDTO> content;
}
