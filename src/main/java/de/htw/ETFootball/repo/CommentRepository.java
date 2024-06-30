package de.htw.ETFootball.repo;

import de.htw.ETFootball.web.API.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

}
