package com.example.lab_11;

import com.example.lab_11.repository.MaterialRepository;
import com.example.lab_11.service.GraphQueryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab11Application {
	static void main(String[] args) {
		SpringApplication.run(Lab11Application.class, args);
	}

	@Bean
	CommandLineRunner runner(GraphQueryService queryService, MaterialRepository materialRepository) {
		return args -> {
			queryService.executeAllQueries();
            materialRepository.findById(1L).ifPresent(material -> System.out.println("Found material through Repository: " + material.getTitle() + " (Price: " + material.getCost() + ")"));
        };
	}
}