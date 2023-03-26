package br.com.keepmygaming.gameservice.dtos;

import br.com.keepmygaming.gameservice.models.Platform;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@RedisHash(value = "games", timeToLive = 120)
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
