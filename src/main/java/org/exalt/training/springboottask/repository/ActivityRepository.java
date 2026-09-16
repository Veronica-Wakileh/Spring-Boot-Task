package org.exalt.training.springboottask.repository;

import org.exalt.training.springboottask.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, Long> {
}
