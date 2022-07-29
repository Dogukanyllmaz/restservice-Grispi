package com.grispi.bootcamp.restservice.controller;

import com.grispi.bootcamp.restservice.model.Movie;
import com.grispi.bootcamp.restservice.model.Player;
import com.grispi.bootcamp.restservice.model.SimpleMovie;
import com.grispi.bootcamp.restservice.repository.MovieRepository;
import com.grispi.bootcamp.restservice.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/movies/")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public MovieController(MovieRepository movieRepository, PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        this.movieRepository = movieRepository;
    }

//---------------------------------------Movie Methods------------------------------------------------

    @GetMapping("")
    public Set<Movie> getMoviesWithRelations() {
        return movieRepository.findAllWithRelations();
    }

    @GetMapping("simple")
    public List<SimpleMovie> getMovies() {
        List<Movie> movies = (List<Movie>) movieRepository.findAll();
        List<SimpleMovie> simpleMovies = movies.stream().map(m -> new SimpleMovie(m)).collect(Collectors.toList());
        return simpleMovies;
    }

    @PostMapping("")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        movieRepository.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }

    @GetMapping("{id}")
    public ResponseEntity<Movie> getById(@PathVariable Long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalMovie.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Movie> deleteById(@PathVariable Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie)  {
        Optional<Movie> movie1 = movieRepository.findById(id);
        if (movie1.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        movie1.get().setGenres(movie.getGenres());

        //movie1.get().setPlayers(movie.getPlayers());

        movieRepository.save(movie1.get());
        return ResponseEntity.status(HttpStatus.OK).body(movie1.get());
    }

    // -------------------------------------- Player Methods -------------------------------------

    @PostMapping("{id}/players")
    public ResponseEntity<Set<Player>> createPlayersOfMovie(@PathVariable long id, @RequestBody List<Player> players){
        if (!movieRepository.existsById(id))
            return ResponseEntity.notFound().build();
        Optional<Movie> movie = movieRepository.findById(id);
        players.forEach(player ->  {
            if (player.getId() == null) {
                playerRepository.save(player);
                movie.get().getPlayers().add(player);
            }
            else if (playerRepository.existsById(id)) {
                movie.get().getPlayers().add(player);
            }
        });
        movieRepository.save(movie.get());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{id}/players")
    public Set<Player> playersOfMovie (@PathVariable Long id) {
        return movieRepository.findById(id).get().getPlayers();
    }



}




