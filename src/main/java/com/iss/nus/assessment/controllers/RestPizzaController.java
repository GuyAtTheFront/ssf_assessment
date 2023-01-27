package com.iss.nus.assessment.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.iss.nus.assessment.repositories.RedisRepo;

import jakarta.json.Json;

@RestController
public class RestPizzaController {
    
    @Autowired
    RedisRepo redis;

    @GetMapping(path="/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOrderById(@PathVariable String id) {

        Optional<String> response = redis.getRecordById(id);

        if (response.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Json.createObjectBuilder()
                                .add("message", "Order %s not found".formatted(id))
                                .build().toString());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.get());
    }
}
