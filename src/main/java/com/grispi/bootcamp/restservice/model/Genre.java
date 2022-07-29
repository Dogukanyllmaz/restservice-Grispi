package com.grispi.bootcamp.restservice.model;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;



@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    protected Genre() {

    }

    public Genre(Long id,String name) {
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }



}
