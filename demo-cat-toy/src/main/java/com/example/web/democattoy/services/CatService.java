package com.example.web.democattoy.services;

import com.example.web.democattoy.beans.Cat;
import com.example.web.democattoy.exceptions.DemoCatToyException;

import java.util.List;

public interface CatService {

    void addCat(Cat cat);

    void updateCat(int id, Cat cat) throws DemoCatToyException;

    void deleteCat(int id) throws DemoCatToyException;

    Cat getOneCat(int id) throws DemoCatToyException;

    List<Cat> getAllCats();

    Cat getCatByNameAndWeight(String name, float weight) throws DemoCatToyException; // return a List<Cat> is better

    Cat getCatByName(String name); // return a List<Cat> is better

    Cat getCatByWeight(double weight); // return a List<Cat> is better

    Cat getCatByNameOrWeight(String name, double weight); // return a List<Cat> is better

    List<Cat> findByNameStartingWith(String prefix);

    List<Cat> findByWeightOrderByWeight(double weight);
    List<Cat> findByWeightOrderByWeightDesc(double weight);
}
