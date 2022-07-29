package com.grispi.bootcamp.restservice.model;

public class SimpleMovie {

    private String name;
    private String imdbKey;

    public SimpleMovie(Movie movie) {
        this.name = movie.getName();
        this.imdbKey = movie.getImdbKey();
    }

    public String getName() {
        return name;
    }

    public String getImdbKey() {
        return imdbKey;
    }
}
