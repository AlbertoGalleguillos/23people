package com.example.people;

import org.springframework.data.repository.CrudRepository;

public interface PeopleRepository extends CrudRepository<Persona, Long> {
}
