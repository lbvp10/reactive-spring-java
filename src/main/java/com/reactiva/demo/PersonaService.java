package com.reactiva.demo;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactiva.demo.modelo.Persona;

import reactor.core.publisher.Flux;

@Service
public class PersonaService {

	@Autowired
	PersonaRepository personaRepository;
	
	@Autowired
	TransaccionalBatch<Persona> transaccionalBatch;
	
	
	
	public Flux<Persona> guardarPersona(List<Persona> personas) {
		Flux<Persona> flux = Flux.create(a->{
			personas.forEach(persona-> a.next(personaRepository.save(persona) ));
			a.complete();
		});
		
		return flux;
	}




	public Collection<Persona> bulkSave(List<Persona> personas) {
		transaccionalBatch.saveAll(personas);
		return personas;
	}
	
	


	
	
	
}
