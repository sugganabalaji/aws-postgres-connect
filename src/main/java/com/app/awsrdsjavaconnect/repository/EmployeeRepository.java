package com.app.awsrdsjavaconnect.repository;

import com.app.awsrdsjavaconnect.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}

