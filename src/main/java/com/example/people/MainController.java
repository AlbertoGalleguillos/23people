package com.example.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private PeopleRepository peopleRepository;

    @GetMapping("/people")
    public Iterable<Persona> getAllPeople() {
        return peopleRepository.findAll();
    }

    @PostMapping("/people")
    public Persona createPerson(@RequestBody Persona persona) {
        return peopleRepository.save(persona);
    }

    @GetMapping("/people/{id}")
    public Persona getPerson(@PathVariable(value = "id") Long id) {
        return peopleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person", "id", id));
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Object> updatePerson(@RequestBody Persona persona, @PathVariable long id) {

        Optional<Persona> personaOptional = peopleRepository.findById(id);

        if (!personaOptional.isPresent())
            return ResponseEntity.notFound().build();

        persona.setId(id);
        peopleRepository.save(persona);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id) {
        Persona persona = peopleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person", "id", id));

        peopleRepository.delete(persona);

        return ResponseEntity.ok().build();
    }
}