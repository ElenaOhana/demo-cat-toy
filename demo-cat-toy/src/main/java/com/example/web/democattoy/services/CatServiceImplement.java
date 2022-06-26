package com.example.web.democattoy.services;

import com.example.web.democattoy.beans.Cat;
import com.example.web.democattoy.exceptions.DemoCatToyException;
import com.example.web.democattoy.exceptions.ErrMsg;
import com.example.web.democattoy.repositories.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CatServiceImplement implements CatService{

    @Autowired
    private CatRepository catRepository;

    @Override
    public void addCat(Cat cat) {
        catRepository.save(cat);
    }

    @Override
    public void updateCat(int id, Cat cat) throws DemoCatToyException {
        if (!catRepository.existsById(id)) {
            throw new DemoCatToyException(ErrMsg.ID_NOT_FOUND);
        }
        catRepository.updateCat(cat.getName(), cat.getWeight(), cat.getId());
    }

    @Override
    public void deleteCat(int id) throws DemoCatToyException {
        if (!catRepository.existsById(id)) {
            throw  new DemoCatToyException(ErrMsg.ID_NOT_FOUND);
        }
        catRepository.deleteById(id);
    }

    @Override
    public Cat getOneCat(int id) throws DemoCatToyException {
        return catRepository.findById(id).orElseThrow(()-> new DemoCatToyException(ErrMsg.ID_NOT_FOUND));
    }

    @Override
    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }

    @Override
    public Cat getCatByNameAndWeight(String name, float weight) throws DemoCatToyException {
        if (weight < 0) {
            throw new DemoCatToyException(ErrMsg.WEIGHT_ID_NEGATIVE);
        }
        return catRepository.findByNameAndWeight(name, weight);
    }

    @Override
    public Cat getCatByName(String name) {
        return catRepository.findByName(name);
    }

    @Override
    public Cat getCatByWeight(double weight) {
        return catRepository.findByWeight(weight);
    }

    @Override
    public Cat getCatByNameOrWeight(String name, double weight) {
        return catRepository.getCatByNameOrWeight(name, weight);
    }

    @Override
    public List<Cat> findByNameStartingWith(String prefix) {
        return catRepository.findByNameStartingWith(prefix);
    }

    @Override
    public List<Cat> findByWeightOrderByWeight(double weight) {
        return catRepository.findByWeightOrderByWeight(weight);
    }

    @Override
    public List<Cat> findByWeightOrderByWeightDesc(double weight) {
        return catRepository.findByWeightOrderByWeightDesc(weight);
    }

}
