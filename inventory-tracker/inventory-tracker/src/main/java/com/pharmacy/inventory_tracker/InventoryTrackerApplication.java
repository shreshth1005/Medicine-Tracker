package com.pharmacy.inventory_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories; // <-- IMPORT THIS

@SpringBootApplication
@EnableJpaRepositories // <-- ADD THIS ANNOTATION
public class InventoryTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryTrackerApplication.class, args);
    }

}
