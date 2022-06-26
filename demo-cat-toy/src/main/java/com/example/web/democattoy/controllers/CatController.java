package com.example.web.democattoy.controllers;

import com.example.web.democattoy.beans.Cat;
import com.example.web.democattoy.exceptions.DemoCatToyException;
import com.example.web.democattoy.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cats")
public class CatController {

    @Autowired
    private CatService catService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cat getOneCatById(@PathVariable int id) throws DemoCatToyException {
        return catService.getOneCat(id);
    }

    @PostMapping
    public ResponseEntity<?> addCat(@RequestBody Cat cat) {
        catService.addCat(cat);
        return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCatById(@PathVariable int id, @RequestBody Cat cat) throws DemoCatToyException {
        catService.updateCat(id, cat);
        return new ResponseEntity<Cat>(cat, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCatById(@PathVariable int id) throws DemoCatToyException {
        catService.deleteCat(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Cat> getAllCats(){
        return catService.getAllCats();
    }

    @GetMapping("by-name-and-weight")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cat getCatByNameAndWeight(@RequestParam String name, @RequestParam float weight) throws DemoCatToyException {
        return catService.getCatByNameAndWeight(name, weight);
    }

    @GetMapping("by-name")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cat getCatByName(@RequestParam String name){
        return catService.getCatByName(name);
    }

    @GetMapping("by-weight")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Cat getCatByWeight(@RequestParam float weight){
        return catService.getCatByWeight(weight);
    }

    @GetMapping("{name}/{weight}") //example of other possibility to do @GetMapping("by-name-and-weight") OR @GetMapping("books/{start}/{end}")
    public ResponseEntity<?> getCatByNameOrWeight(@PathVariable String name, @PathVariable double weight) {
        return new ResponseEntity<>(catService.getCatByNameOrWeight(name, weight), HttpStatus.OK);
    }

    @GetMapping("by-prefix")
    public ResponseEntity<?> findByNameStartingWith(@RequestParam String prefix) {
        return new ResponseEntity<>(catService.findByNameStartingWith(prefix), HttpStatus.OK);
    }

    @GetMapping("{order-by-weight}")
    public List<Cat> findByWeightOrderByWeight(@PathVariable double weight) {
        return catService.findByWeightOrderByWeight(weight);
    }

    @GetMapping("{order-by-weight-desc}")
    public List<Cat> findByWeightOrderByWeightDesc(@PathVariable double weight) {
        return catService.findByWeightOrderByWeightDesc(weight);
    }
}
