package com.andersontaraujo.keepmygaming.games.repositories;

import com.andersontaraujo.keepmygaming.games.dtos.GameResponseDTO;
import org.springframework.data.repository.CrudRepository;

public interface GameResponseRepository extends CrudRepository<GameResponseDTO, String> {
}
