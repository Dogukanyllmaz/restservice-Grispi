package com.grispi.bootcamp.restservice.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;







@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //@NotBlank(message = "Name is mandatory")
    @Column(nullable = false)
    private String name;
    private String imdbKey;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Genre> genres = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Player> players = new HashSet<>();

    protected Movie() {
    }

    public Movie(String name, String imdbKey, Set<Genre> genres, Set<Player> players) {
        this.name = name;
        this.imdbKey = imdbKey;
        this.genres = genres;
        this.players = players;
    }

    public Long getId() { return id; }

    public String getName() {
        return name;
    }

    public String getImdbKey() {
        return imdbKey;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}