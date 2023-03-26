package br.com.keepmygaming.gameservice.dtos;

import br.com.keepmygaming.gameservice.models.Platform;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class UpdateGameRequestDTO {
    @NotBlank(message = "The field 'name' must be fulfilled")
    private String name;
    private String publisherName;
    private String developerName;
    @NotNull(message = "The field 'yearOfRelease' must be fulfilled")
    private Integer yearOfRelease;
    @NotEmpty(message = "The field 'platforms' must have at least one value")
    private List<Platform> platforms;
}
