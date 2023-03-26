package br.com.keepmygaming.gameservice.repositories;

import br.com.keepmygaming.gameservice.dtos.GameResponseDTO;
import org.springframework.data.repository.CrudRepository;

public interface GameResponseRepository extends CrudRepository<GameResponseDTO, String> {
}
