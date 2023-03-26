package br.com.keepmygaming.gameservice.repositories;

import br.com.keepmygaming.gameservice.models.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GameCustomRepositoryImpl implements GameCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Game> searchGames(String name, String publisherName, Integer yearOfRelease, int page, int size, String sortByField, String sortByDirection) {
        Pageable pageable = PageRequest.of(page-1, size);

        Query query = new Query()
                .with(pageable)
                .with(Sort.by(Sort.Direction.fromString(sortByDirection), sortByField));

        if (name != null) {
            query.addCriteria(Criteria.where("name").regex(name));
        }

        if (publisherName != null) {
            query.addCriteria(Criteria.where("publisherName").regex(publisherName));
        }

        if (yearOfRelease != null) {
            query.addCriteria(Criteria.where("yearOfRelease").is(yearOfRelease));
        }

        List<Game> content = mongoTemplate.find(query, Game.class);

        return PageableExecutionUtils.getPage(
                content,
                pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Game.class));
    }
}
