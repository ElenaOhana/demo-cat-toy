package com.example.web.democattoy.dto;

import com.example.web.democattoy.beans.Toy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatDTO {
    private String name;
    double weight;
    Collection<Toy> toys = new ArrayList<>();
}
