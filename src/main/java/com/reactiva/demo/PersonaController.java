package com.reactiva.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reactiva.demo.modelo.Persona;

import reactor.core.publisher.Flux;

@RestController("/persona")
public class PersonaController {

	
	private final PersonaService personaService;
	
	
	
	@Autowired
	public PersonaController(PersonaService personaService) {
		super();
		this.personaService = personaService;
	}


	@RequestMapping(method = RequestMethod.GET,produces = "text/event-stream")
	public Flux<Persona> guardarPersona() {
		List<Persona> personas = new ArrayList<>();
		
		for (int i = 0; i < 300_000; i++) {
			personas.add(new Persona(i + "", i + ""));
		}
		
		
		Flux<Persona> personasFlux = personaService.guardarPersona(personas).repeat().map(n->n);
		
		return personasFlux;
	}
	
	
	@RequestMapping(method = RequestMethod.GET,path="/batch" )
	public Collection<Persona> guardarBatch() {
		
      List<Persona> personas = new ArrayList<>();
		
		for (int i = 0; i < 300_000; i++) {
			personas.add(new Persona(i + "", i + ""));
		}
		
		return personaService.bulkSave(personas);
		
	}


}
