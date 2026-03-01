package org.example.budget_tracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class startupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        // paste your old Main.main logic here:
        login log = new login();
        log.userLogin();
    }
}