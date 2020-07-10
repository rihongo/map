package com.rihongo.map.model.repository;

import com.rihongo.map.model.UserSession;
import org.springframework.data.repository.CrudRepository;

public interface UserRedisRepository extends CrudRepository<UserSession, Long> {
}
