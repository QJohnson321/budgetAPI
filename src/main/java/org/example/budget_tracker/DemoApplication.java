package org.example.budget_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);


    }
    @Bean
    CommandLineRunner seedCategories(CategoryRepository categoryRepo) {
        return args -> {
            if (categoryRepo.count() == 0) {
                categoryRepo.save(new Category("Food"));
                categoryRepo.save(new Category("Gas"));
                categoryRepo.save(new Category("Travel"));
                categoryRepo.save(new Category("Bills"));
            }
        };
    }


}
