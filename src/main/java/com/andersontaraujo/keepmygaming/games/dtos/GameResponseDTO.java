package com.andersontaraujo.keepmygaming.games.dtos;

import com.andersontaraujo.keepmygaming.games.models.Platform;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GameResponseDTO {
    private String id;
    private String name;
    private String publisherName;
    private String developerName;
    private Integer yearOfRelease;
    private List<Platform> platforms;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedDate;
}
