package fr.efrei.pokemon.controller;

import fr.efrei.pokemon.dto.CreateTrainer;
import fr.efrei.pokemon.dto.UpdateTrainer;
import fr.efrei.pokemon.models.Trainer;
import fr.efrei.pokemon.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    // GET all trainers
    @GetMapping
    public ResponseEntity<List<Trainer>> findAll() {
        return new ResponseEntity<>(trainerService.findAll(), HttpStatus.OK);
    }

    // GET trainer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Trainer> findById(@PathVariable String id) {
        Trainer trainer = trainerService.findById(id);
        if (trainer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainer, HttpStatus.OK);
    }

    // POST create new trainer
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CreateTrainer createTrainer) {
        Trainer trainer = trainerService.save(createTrainer);
        return new ResponseEntity<>(trainer, HttpStatus.CREATED);
    }

    // PATCH update trainer
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateTrainer updateTrainer) {
        Trainer trainer = trainerService.findById(id);
        if (trainer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        trainerService.update(id, updateTrainer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // DELETE trainer
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Trainer trainer = trainerService.findById(id);
        if (trainer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        trainerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
