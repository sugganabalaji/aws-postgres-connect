package com.app.awspostgresconnect.service;

import com.app.awspostgresconnect.dto.EmployeeDTO;
import com.app.awspostgresconnect.entity.Employee;
import com.app.awspostgresconnect.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> findAll() {
        logger.info("findAll called");
        return repository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        logger.info("findById called with id: " + id);
        return repository.findById(id);
    }

    public Employee save(EmployeeDTO employeeDTO) {
        logger.info("save called");
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return repository.save(employee);
    }

    public Employee update(Long id, EmployeeDTO employeeDTO) {
        logger.info("update called");
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employee.setId(id);
        return repository.save(employee);
    }

    public boolean delete(Long id) {
        logger.info("delete called with id: " + id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public int findEmployeesCount() {
        logger.info("findEmployeesCount called");
        List<Employee> employees = repository.findAll();
        if(employees.isEmpty()) {
            logger.info("EmployeesCount: 0");
            return 0;
        }
        logger.info("EmployeesCount: " + employees.size());
        return employees.size();
    }
}
