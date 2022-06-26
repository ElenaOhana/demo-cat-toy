package com.example.web.democattoy.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "toys")
@Builder
public class Toy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    /*@ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @JsonIgnore
    //@JoinColumn(name = "cat_id")
    private Cat cat;*/

    @NonNull
    @Column(nullable = false, length = 40)
    private String name;

    public Toy(String name) {
        this.name = name;
    }
}
