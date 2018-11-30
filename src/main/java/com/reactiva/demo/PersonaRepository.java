package com.reactiva.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.reactiva.demo.modelo.Persona;

@Repository
interface  PersonaRepository extends CrudRepository<Persona, Long> {
 
}
