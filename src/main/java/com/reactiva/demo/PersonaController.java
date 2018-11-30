package com.reactiva.demo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.reactivestreams.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactiva.demo.modelo.Persona;

import reactor.core.publisher.Flux;

@RestController("/persona")
public class PersonaController {

	@Autowired
	PersonaService personaService;

	@GetMapping(produces = "text/event-stream")
	public Flux<Persona> guardarPersona() {
		List<Persona> personas = new ArrayList<>();
		
		for (int i = 0; i < 1000; i++) {
			personas.add(new Persona(i + "", i + ""));
		}
		
		imprimirHora();
		
		Flux<Persona> personasFlux = personaService.guardarPersona(personas).repeat().map(n->n);

		
		imprimirHora();
		
		return personasFlux;
	}


	private void imprimirHora() {
		Calendar calendario = Calendar.getInstance();
		int hora = calendario.get(Calendar.HOUR_OF_DAY);
		int minutos = calendario.get(Calendar.MINUTE);
		int segundos = calendario.get(Calendar.SECOND);
		System.out.println(hora + ":" + minutos + ":" + segundos);
	}
}
