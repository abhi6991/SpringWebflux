package com.abhishek.personService.api;

import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.abhishek.personService.reactiveWebserver.JsonWriter;
import com.abhishek.personService.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;

import reactor.core.publisher.Mono;

public class PersonApi {
	public final RouterFunction<ServerResponse> routerFunction;

	private final PersonService personService;

	public PersonApi(PersonService personService) {
		this.personService = personService;
		this.routerFunction = RouterFunctions.route(RequestPredicates.GET(""), this::getAllPersons);
	}

	private Mono<ServerResponse> getAllPersons(ServerRequest request) {
		return this.personService.list() // returns a Flux<Person>
				.collectList()
				.flatMap(JsonWriter::write)
				.flatMap((json) -> ServerResponse.ok().body(Mono.just(json), String.class))
				.onErrorResume(JsonProcessingException.class, (e) -> ServerResponse.status(500)
						.body(Mono.just(e.getMessage()), String.class));
	}
}
