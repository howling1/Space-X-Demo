package edu.tum.backend.controller;

import edu.tum.backend.Service.ShipService;
import edu.tum.backend.model.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ship")
public class ShipController {
    @Autowired
    ShipService shipService;

    @GetMapping("/{name}")
    public Ship findShip(@PathVariable String name){
        return shipService.findShipByName(name);
    }

    @GetMapping("/fuzzy/{name}")
    public List<Ship> fuzzyFindShips(@PathVariable String name){
        return  shipService.fuzzyFindShips(name);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveShips(@RequestBody List<Ship> ships){
        try {
            shipService.saveShips(ships);
            return new ResponseEntity<String>("All ships have been saved", HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("Save failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clear")
    public ResponseEntity<String> clearShips(){
        try {
            shipService.deleteAllShips();
            return new ResponseEntity<String>("All ships have been deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Clear failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addNewShip(@RequestBody Ship ship){
        try {
            shipService.addOne(ship);
            return new ResponseEntity<Object>(shipService.findAllShips(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Object>("Add failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deleteShip(@PathVariable String id){
        try {
            shipService.deleteOne(id);
            return new ResponseEntity<Object>(shipService.findAllShips(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Object>("Delete failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public List<Ship> findAllShips(){
        return shipService.findAllShips();
    }
}
