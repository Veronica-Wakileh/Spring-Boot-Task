package org.exalt.training.springboottask.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.xml.stream.events.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, Long> {
}
