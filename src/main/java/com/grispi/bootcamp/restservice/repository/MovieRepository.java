package com.grispi.bootcamp.restservice.repository;

import com.grispi.bootcamp.restservice.model.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;


public interface MovieRepository extends CrudRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.genres g LEFT JOIN FETCH m.players p")
    Set<Movie> findAllWithRelations();

}
