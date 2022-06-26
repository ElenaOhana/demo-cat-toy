package com.example.web.democattoy.clr;

import com.example.web.democattoy.beans.Cat;
import com.example.web.democattoy.beans.Toy;
import com.example.web.democattoy.repositories.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

//@Component
public class UseCase2RestTemp implements CommandLineRunner {

    @Autowired
    RestTemplate restTemplate;

   /* @Autowired
    CatRepository catRepository;*/

    private static final String URL = "http://localhost:8080/cat-toy-app/cats/";

    @Override
    public void run(String... args) throws Exception {

        System.out.println("////////////RestTemplate///////////////");
        Toy toy = new Toy("Grey mouse");
        Toy toy2 = new Toy("Hang mouse");

        Cat kitty = Cat.builder()
                .name("Kitty")
                .weight(4.85)
                .toy(toy)
                .toy(toy2)
                .build();

        ResponseEntity<String> res = restTemplate.postForEntity(URL, kitty, String.class);
        System.out.println(res.getStatusCode());
        System.out.println(res);

        Cat[] cats = restTemplate.getForObject(URL, Cat[].class);
        Arrays.stream(cats).forEach(System.out::println);

        /*Optional<Cat> forprint = catRepository.findById(1);// HERE it is new! UPDATED!
        System.out.println("Get UPDATED CAT by id: "+forprint);*/
    }
}
