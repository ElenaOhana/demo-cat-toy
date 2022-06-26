package com.example.web.democattoy.repositories;

import com.example.web.democattoy.beans.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat,Integer> {

    @Modifying
    @Query(value = "UPDATE Cat c SET c.name=?1, c.weight= ?2 WHERE c.id=?3"/*, nativeQuery = true*/)
    void updateCat(String name, double weight, int id);

    Cat findByNameAndWeight(String name, double weight);

    Cat findByName(String name);

    Cat findByWeight(double weight);

    Cat getCatByNameOrWeight(String name, double weight);

    List<Cat> findByNameStartingWith(String prefix);

    List<Cat> findByWeightOrderByWeight(double weight);
    List<Cat> findByWeightOrderByWeightDesc(double weight);
}
