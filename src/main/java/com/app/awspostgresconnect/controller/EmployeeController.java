package com.app.awspostgresconnect.controller;

import com.app.awspostgresconnect.dto.EmployeeDTO;
import com.app.awspostgresconnect.entity.Employee;
import com.app.awspostgresconnect.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/employees")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Value("${spring.profiles.active}")
    private String dbName;

    @Autowired
    private EmployeeService service;

    @GetMapping("/ok")
    public ResponseEntity<String> sayOk() {
        logger.info("Ok");
        return ResponseEntity.status(HttpStatus.OK).body("Ok.. :)");
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        logger.info("findAll called");
        List<Employee> allEmployees = service.findAll();
        logger.info("findAll ended");
        if(allEmployees.isEmpty()) {
            return new ResponseEntity<>("Employees not saved in " + dbName, HttpStatus.OK);
        }
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        logger.info("findById called");
        Optional<Employee> optional = service.findById(id);
        if(optional.isPresent()) {
            logger.info("findById ended");
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else {
            logger.error("Employee not found with id: " + id + " in " + dbName);
            return new ResponseEntity<>("Employee not found with id: " + id + " in " + dbName, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody EmployeeDTO employee) {
        logger.info("save started");
        Employee savedEmployee = service.save(employee);
        logger.info("save ended");
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody EmployeeDTO employee) {
        logger.info("update called with id: " + id);
        if(service.findById(id).isPresent()) {
            Employee updated = service.update(id, employee);
            logger.info("update ended");
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        logger.error("Employee not found with id: " + id + " in " + dbName);
        return new ResponseEntity<>("Employee with id: " + id + " not found in " + dbName, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        logger.info("delete called");
        boolean isDeleted = service.delete(id);
        if(isDeleted) {
            logger.info("Employee with id: " + id + " successfully deleted");
            logger.info("delete ended");
            return new ResponseEntity<>("Employee with id: " + id + " successfully deleted", HttpStatus.OK);
        }
        logger.error("Employee with id: " + id + " not found in " + dbName);
        logger.info("delete ended");
        return new ResponseEntity<>("Employee with id: " + id + " not found in " + dbName, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<String> findEmployeesCount() {
        logger.info("findByName called");
        int i = service.findEmployeesCount();
        logger.info("findByName ended");
        return new ResponseEntity<>("Total Employees count is " + i + " in " + dbName, HttpStatus.OK);
    }

}
