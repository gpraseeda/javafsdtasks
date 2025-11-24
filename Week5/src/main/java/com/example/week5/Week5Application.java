package com.example.week5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Week5Application {

	public static void main(String[] args) {
		SpringApplication.run(Week5Application.class, args);
	}

}


@RestController
class HelloController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}
}
