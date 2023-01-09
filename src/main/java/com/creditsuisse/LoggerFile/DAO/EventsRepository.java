package com.creditsuisse.LoggerFile.DAO;

import com.creditsuisse.LoggerFile.Models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends CrudRepository<Event, String> {
}
