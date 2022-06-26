package com.example.web.democattoy.clr;

import com.example.web.democattoy.beans.Cat;
import com.example.web.democattoy.beans.Toy;
import com.example.web.democattoy.controllers.CatController;
import com.example.web.democattoy.repositories.CatRepository;
import com.example.web.democattoy.repositories.ToyRepository;
import com.example.web.democattoy.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional
@Component
@Order(1)
public class UseCase1 implements CommandLineRunner {

    @Autowired
    private CatService catService;

    @Autowired
    private CatRepository catRepository;

    @Autowired
    ToyRepository toyRepository;

    @Autowired
    CatController catController;

    @Override
    public void run(String... args) throws Exception {

        Toy toy = Toy.builder()
                .name("Bol")
                .build();

        Toy toy2 = Toy.builder()
                .name("Bol2")
                .build();

        Toy toy3 = Toy.builder()
                .name("Bol3")
                .build();

        Toy toy4 = Toy.builder()
                .name("Bol4")
                .build();

        Toy toy5 = Toy.builder()
                .name("Bol5")
                .build();

        Toy toy6 = Toy.builder()
                .name("Bol6")
                .build();
        Cat cat1ToDb = Cat.builder()
                .name("Pitzi")
                .weight(4)
                .toy(toy)
                .toy(toy2)
                .build();

        /*toy.setCat(cat1ToDb);
        toy2.setCat(cat1ToDb);*/

        Cat cat2ToDb = Cat.builder()
                .name("Tommy")
                .weight(4.9)
                .toy(toy3)
                .toy(toy4)
                .build();

       /* toy3.setCat(cat2ToDb);
        toy4.setCat(cat2ToDb);*/

        Cat cat3ToDb = Cat.builder()
                .name("Snegok")
                .weight(5)
                .toy(toy5)
                .toy(toy6)
                .build();

        /*toy5.setCat(cat3ToDb);
        toy6.setCat(cat3ToDb);*/

        catRepository.saveAll(Arrays.asList(cat1ToDb, cat2ToDb, cat3ToDb));
        catService.getAllCats().forEach(System.out ::println);

        System.out.println("////////////Get cat by ....//////////////");
        System.out.println(catService.getCatByNameAndWeight("pitzi", 4));
        System.out.println(catService.getCatByName("tommy"));
        System.out.println(catService.getCatByWeight( 5));
        System.out.println(catService.getCatByWeight( 4.9));//TODO WHY does spring doesn't find fractional float/double number(not round number)
        System.out.println(catService.getCatByNameOrWeight("Some Name", 5));
        Cat newCat = Cat.builder()
                .id(1) //TODO PAY ATTENTION TO PUT ID WHEN YOU GO TO YOUR UPDATE METHOD!!!
                .name("Snow")
                .weight(3.5)
                .toy(toy)
                .toy(toy)
                .build();
       catService.updateCat(1, newCat);
       Cat forprint = catService.getOneCat(1);//Look like: In heap still the same object! .Издец!!!
       System.out.println("Get UPDATED CAT by id: "+forprint);//Look like: In heap still the same object! .Издец!!!
       System.out.println("Get UPDATED CAT by id: "+catRepository.findById(1));//Look like: In heap still the same object! .Издец!!!
       System.out.println("Get UPDATED CAT by id: "+ catRepository.findByName("Snow"));//Look like: In heap still the same object! .Издец!!!

        System.out.println("GET BY STARTING WITH: ");
        System.out.println(catService.findByNameStartingWith("Sn"));//Steel did not refresh the heap for Cat id=1
        List<Cat> freshCatList = catService.findByNameStartingWith("Sn");
        //System.out.println(freshCatList);
        System.out.println(catController.findByNameStartingWith("Tom"));

        System.out.println(catController.findByWeightOrderByWeight(3));// []  ??
        System.out.println(catController.findByWeightOrderByWeightDesc(2));// []  ??
    }
}
