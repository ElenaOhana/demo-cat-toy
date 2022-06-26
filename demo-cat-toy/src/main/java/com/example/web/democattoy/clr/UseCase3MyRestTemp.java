package com.example.web.democattoy.clr;

import com.example.web.democattoy.beans.Cat;
import com.example.web.democattoy.beans.Toy;
import com.example.web.democattoy.services.CatServiceViaRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

//@Component
public class UseCase3MyRestTemp implements CommandLineRunner {
    @Autowired
    CatServiceViaRestTemplate catServiceViaRestTemplate;

    private static final String URL = "http://localhost:8080/cat-toy-app/cats/";
    Toy toy = new Toy("Grey mouse");
    Toy toy2 = new Toy("Hang mouse");

    Cat kitty = Cat.builder()
            .name("Kitty")
            .weight(4.85)
            .toy(toy)
            .toy(toy2)
            .build();

    @Override
    public void run(String... args) throws Exception {
        ResponseEntity<?> responseCatEntity = catServiceViaRestTemplate.postCatToManyProviders(URL, kitty);
        System.out.println(responseCatEntity.toString());

        ResponseEntity<?> responseCatsFromDb = catServiceViaRestTemplate.getCatsFromManyProviders(URL);
        System.out.println(responseCatsFromDb.toString());
        Cat[] cats = (Cat[]) responseCatsFromDb.getBody();
        assert cats != null;
        for (Cat cat : cats) {
            System.out.println(cat);
        }
        //Arrays.asList(cats).forEach(System.out :: println);
    }
}
