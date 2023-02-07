package com.andersontaraujo.keepmygaming.games.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "games")
public class Game {
    @Id
    private String id;
    private String name;
    private String publisherName;
    private String developerName;
    private Integer yearOfRelease;
    private List<Platform> platforms;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime updatedDate;
}
