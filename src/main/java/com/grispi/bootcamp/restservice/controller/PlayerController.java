package com.grispi.bootcamp.restservice.controller;

import com.grispi.bootcamp.restservice.model.Player;
import com.grispi.bootcamp.restservice.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players/")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return (List<Player>) playerRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<List<Player>> createPlayer(@RequestBody List<Player> player) {
        playerRepository.saveAll(player);
        return ResponseEntity.status(HttpStatus.CREATED).body((List<Player>)playerRepository.findAll());
    }


}
