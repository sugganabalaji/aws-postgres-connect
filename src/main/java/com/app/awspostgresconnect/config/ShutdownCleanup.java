package com.app.awspostgresconnect.config;

import com.app.awspostgresconnect.repository.EmployeeRepository;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class ShutdownCleanup {

    private final EmployeeRepository repository;

    public ShutdownCleanup(EmployeeRepository repository) {
        this.repository = repository;
    }

    @PreDestroy
    public void clearDatabaseBeforeShutdown() {
        System.out.println("Deleting all Employee entries before shutdown...");
        repository.deleteAll();
    }

}
