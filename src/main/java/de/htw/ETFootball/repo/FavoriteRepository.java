package de.htw.ETFootball.repo;

import de.htw.ETFootball.web.API.Favorite;
import org.springframework.data.repository.CrudRepository;

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {
}
