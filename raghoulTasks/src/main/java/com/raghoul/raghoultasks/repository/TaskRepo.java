package com.raghoul.raghoultasks.repository;

import com.raghoul.raghoultasks.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepo extends MongoRepository<Task, UUID> {

    List<Task> findByOwnerId(UUID ownerId);

    boolean existsByOwnerId(UUID ownerId);
}
