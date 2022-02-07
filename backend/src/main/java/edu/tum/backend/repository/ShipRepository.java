package edu.tum.backend.repository;

import edu.tum.backend.model.Ship;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShipRepository extends MongoRepository<Ship, String>{
    @Override
    Ship save(Ship entity);

    Ship findByName(String name);

    Ship findShipById(String id);

}
