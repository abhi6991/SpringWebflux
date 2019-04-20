package com.abhishek.personService;

import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.abhishek.personService.api.PersonApi;
import com.abhishek.personService.service.PersonService;

import reactor.ipc.netty.http.server.HttpServer;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		final PersonApi personApi = new PersonApi(new PersonService());
		final RouterFunction<ServerResponse> apiRoot = RouterFunctions.nest(RequestPredicates.path("/persons"),
				personApi.routerFunction);
		final HttpHandler httpHandler = RouterFunctions.toHttpHandler(apiRoot);
		final ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

		HttpServer.create(8080).startAndAwait(adapter);
	}
}
