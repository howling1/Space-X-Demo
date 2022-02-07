package edu.tum.backend.Service;

import edu.tum.backend.model.Ship;
import edu.tum.backend.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ShipService {
    @Autowired
    ShipRepository shipRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public void saveShips(List<Ship> ships) throws Exception {
        try {
            for (Ship ship:
                    ships) {
                shipRepository.save(ship);
            }
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    public Ship addOne(Ship ship) throws Exception{
        try {
            if (shipRepository.findShipById(ship.getId()) != null || shipRepository.findByName(ship.getName()) != null){
                throw new Exception("ID or name already exists");
            } else {
                return shipRepository.save(ship);
            }
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    public void deleteOne(String id) throws Exception {
        try {
            shipRepository.deleteById(id);
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    public void deleteAllShips() throws Exception {
        try {
            shipRepository.deleteAll();
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    public Ship findShipByName(String name){
        return shipRepository.findByName(name);
    }

    public List<Ship> fuzzyFindShips(String name){
        Pattern pattern = Pattern.compile("^.*"+  name +".*$", Pattern.CASE_INSENSITIVE);
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(pattern));

        return mongoTemplate.find(query, Ship.class);
    }

    public List<Ship> findAllShips(){
        return shipRepository.findAll();
    }
}
