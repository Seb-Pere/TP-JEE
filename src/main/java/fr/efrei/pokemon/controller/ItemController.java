package fr.efrei.pokemon.controller;

import fr.efrei.pokemon.models.Item;
import fr.efrei.pokemon.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService service;

    @Autowired
    public ItemController(ItemService service) {
        this.service = service;
    }

    // GET
    @GetMapping
    public ResponseEntity<List<Item>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> findById(@PathVariable String id) {
        Item item = service.findById(id);
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    // POST
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Item item) {
        service.save(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Item item) {
        Item existingItem = service.findById(id);
        if (existingItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.update(id, item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Item item = service.findById(id);
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate(@PathVariable String id, @RequestBody Item itemBody) {
        Item item = service.findById(id);
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.partialUpdate(id, itemBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
