package com.andersontaraujo.keepmygaming.games.dtos;

import com.andersontaraujo.keepmygaming.games.models.Platform;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class UpdateGameRequestDTO {
    private String id;
    @NotBlank(message = "name is mandatory")
    private String name;
    private String publisherName;
    private String developerName;
    @NotNull(message = "yearOfRelease is mandatory")
    private Integer yearOfRelease;
    @NotEmpty(message = "platforms is mandatory")
    private List<Platform> platforms;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;
}
