package com.example.web.democattoy.controllers;

import com.example.web.democattoy.beans.Toy;
import com.example.web.democattoy.repositories.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("toys")
public class ToyController {

    @Autowired
    ToyRepository toyRepository;

    @PostMapping
    public ResponseEntity<?> addToy(@RequestBody Toy toy){
        toyRepository.save(toy);
        return new ResponseEntity<>(toy, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping
    public List<Toy> getAllToys(){
        return toyRepository.findAll();
    }
}
