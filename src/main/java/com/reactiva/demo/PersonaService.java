package com.reactiva.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactiva.demo.modelo.Persona;

import reactor.core.publisher.Flux;

@Service
public class PersonaService {

	@Autowired
	PersonaRepository personaRepository;

	
	public Flux<Persona> guardarPersona(List<Persona> personas) {
		Flux<Persona> flux = Flux.create(a->{
			personas.forEach(persona-> a.next(personaRepository.save(persona) ));
			a.complete();
		});
		
		return flux;
	}
}
