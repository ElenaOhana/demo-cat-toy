package com.example.web.democattoy.services;

import com.example.web.democattoy.beans.Cat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CatServiceViaRestTemplate {

    @Autowired
    RestTemplate restTemplate;


    // more generic way to call any REST web-service with any HTTP Method and header is a method exchange()
    public ResponseEntity<?> postCatToManyProviders(String url, Cat catForPost) {
        HttpEntity<Cat> request = new HttpEntity<>(catForPost);
        ResponseEntity<Cat> response = restTemplate.exchange(url, HttpMethod.POST, request, Cat.class);
        assert response.getStatusCode().is2xxSuccessful();
        //status here is different from <return new ResponseEntity status>
        log.info("Called {} and got status {} with content {}", url, response.getStatusCode(), response.getHeaders().getContentType());

        if (response.getStatusCode().is2xxSuccessful()) {
            Cat cat = response.getBody();
            assert cat != null;
            assert cat.getName().equalsIgnoreCase("kitty");
            return new ResponseEntity<>(cat, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error. Something went wrong from POST", HttpStatus.NOT_ACCEPTABLE);
        }
    }


    public ResponseEntity<?> getCatsFromManyProviders(String url) {
        ResponseEntity<Cat[]> response = restTemplate.getForEntity(url, Cat[].class);
        //status here is different from <return new ResponseEntity status>
        log.info("Called {} and got status {} with content {}", url, response.getStatusCode(), response.getHeaders().getContentType());
        if (response.getStatusCode().is2xxSuccessful()) {
            Cat[] cats = response.getBody();//Here retrieving the body that has a Cat[] because, the Cat[] comes on body of response that restTemplate returns.

            assert cats!=null;
            for (Cat book : cats) {
                book.setWeight(book.getWeight() + 0.5);
            }
            return new ResponseEntity<Cat[]>(cats/*body*/, HttpStatus.OK /* => 200*/);
        } else {
            return new ResponseEntity<String>("Error. Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }
        /*
         * getForObject - Converts the response body into an object.
         * getForEntity - Returns the entire response entity
         */
}
