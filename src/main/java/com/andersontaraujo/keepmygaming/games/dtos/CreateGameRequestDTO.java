package com.andersontaraujo.keepmygaming.games.dtos;

import com.andersontaraujo.keepmygaming.games.models.Platform;
import com.andersontaraujo.keepmygaming.games.validators.UniqueName;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class CreateGameRequestDTO {
    @UniqueName
    @NotBlank(message = "The field 'name' must be fulfilled")
    private String name;
    private String publisherName;
    private String developerName;
    @NotNull(message = "The field 'yearOfRelease' must be fulfilled")
    private Integer yearOfRelease;
    @NotEmpty(message = "The field 'platforms' must have at least one value")
    private List<Platform> platforms;
}
