package com.example.web.democattoy.beans;

import lombok.*;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cats")
@Builder
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 40)
    private String name;

    private double weight;

    @OneToMany(/*mappedBy = "cat",*/ cascade = CascadeType.ALL)//TODO does not Update toys when I update the cats with toys(whole object). Why?????
    @Singular
    //@ToString.Exclude
    Collection<Toy> toys = new ArrayList<>();

}
