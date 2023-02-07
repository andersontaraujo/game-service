package com.andersontaraujo.keepmygaming.games.dtos;

import com.andersontaraujo.keepmygaming.games.models.Platform;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class CreateGameRequestDTO {
    @NotBlank(message = "name is mandatory")
    private String name;
    private String publisherName;
    private String developerName;
    @NotNull(message = "yearOfRelease is mandatory")
    private Integer yearOfRelease;
    @NotEmpty(message = "platforms is mandatory")
    private List<Platform> platforms;
}
