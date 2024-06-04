package de.htw.ETFootball.web;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface FavouriteRepository extends ReactiveCrudRepository<Favourite, Long> {
    Mono<Favourite> findByTeamName(String teamName);
}

