package com.vincenzoracca.vr;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class VrApplication {

	@Autowired
	Environment env;

	public static void main(String[] args) {
		SpringApplication.run(VrApplication.class, args);
	}

	@PostConstruct
	public void init() {
		System.out.println("spring.threads.virtual.enabled=" + env.getProperty("spring.threads.virtual.enabled"));
	}

	@RestController
	public class MockApi {

		@GetMapping
		public ResponseEntity<List<Book>> findAll() {
			var books = new ArrayList<Book>();
			for(int i = 0; i < 10; i++) {
				books.add(new Book(i, UUID.randomUUID().toString()));
			}
			return ResponseEntity.ok(books);
		}
	}

	record Book(int bookId, String bookName){}

}
