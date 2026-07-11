package com.app.awspostgresconnect.service;

import com.app.awspostgresconnect.dto.EmployeeDTO;
import com.app.awspostgresconnect.entity.Employee;
import com.app.awspostgresconnect.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    @Test
    void testFindAll() {
        Employee e1 = new Employee();
        e1.setId(1L);
        e1.setName("Balaji");
        e1.setCompanyName("ABC");
        e1.setDepartmentName("Dev");
        e1.setSalary(50000);
        e1.setDateOfJoining(LocalDate.of(2026, 12,30));

        Employee e2 = new Employee();
        e2.setId(2L);
        e2.setName("Suggana");
        e2.setCompanyName("ABC");
        e2.setDepartmentName("HR");
        e2.setSalary(25000);
        e2.setDateOfJoining(LocalDate.of(2025, 11,29));

        List<Employee> employees = List.of(e1, e2);

        when(repository.findAll()).thenReturn(employees);

        List<Employee> result = service.findAll();

        assertNotNull(result.get(0).toString());
        assertEquals(2, result.size());
        assertEquals("Balaji", result.get(0).getName());
        assertEquals("HR", result.get(1).getDepartmentName());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindAll_EmptyList() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<Employee> result = service.findAll();

        assertTrue(result.isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById_Success() {
        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName("Balaji");
        emp.setCompanyName("ABC");
        emp.setDepartmentName("Dev");
        emp.setSalary(50000);
        emp.setDateOfJoining(LocalDate.of(2026, 12,30));

        when(repository.findById(1L)).thenReturn(Optional.of(emp));

        Optional<Employee> result = service.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Balaji", result.get().getName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<Employee> result = service.findById(99L);

        assertFalse(result.isPresent());
        verify(repository, times(1)).findById(99L);
    }

    @Test
    void testSaveEmployee() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Balaji");
        dto.setCompanyName("ABC");
        dto.setDepartmentName("Dev");
        dto.setSalary(50000);
        dto.setDateOfJoining(LocalDate.of(2026, 12,30));

        Employee saved = new Employee();
        saved.setId(1L);
        saved.setName("Balaji");
        saved.setCompanyName("ABC");
        saved.setDepartmentName("Dev");
        saved.setSalary(50000);
        saved.setDateOfJoining(LocalDate.of(2026, 12,30));

        when(repository.save(any(Employee.class))).thenReturn(saved);

        Employee result = service.save(dto);

        assertNotNull(result.getId());
        assertEquals(saved.getId(), result.getId());
        verify(repository, times(1)).save(any(Employee.class));
    }

    @Test
    void testSaveEmployee_NullFields() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Balaji");
        dto.setCompanyName("ABC");
        dto.setDateOfJoining(LocalDate.of(2026, 12,30));

        Employee saved = new Employee();
        saved.setId(2L);
        saved.setName("Balaji");
        saved.setCompanyName("ABC");
        saved.setDateOfJoining(LocalDate.of(2026, 12,30));

        when(repository.save(any(Employee.class))).thenReturn(saved);

        Employee result = service.save(dto);

        assertEquals(2L, result.getId());
        assertNull(result.getDepartmentName());
        assertEquals(0, result.getSalary());
        assertEquals(2026, result.getDateOfJoining().getYear());
    }

    @Test
    void testUpdateEmployee_Success() {
        Employee existing = new Employee();
        existing.setId(1L);
        existing.setName("Balaji");
        existing.setCompanyName("ABC");
        existing.setDepartmentName("Dev");
        existing.setSalary(20000);
        existing.setDateOfJoining(LocalDate.of(2026, 12,30));

        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Balaji");
        dto.setCompanyName("ABC");
        dto.setDepartmentName("SSE");
        dto.setSalary(50000);
        dto.setDateOfJoining(LocalDate.of(2026, 12,30));

        Employee updated = new Employee();
        updated.setId(1L);
        updated.setName("Balaji");
        updated.setCompanyName("ABC");
        updated.setDepartmentName("SSE");
        updated.setSalary(50000);
        updated.setDateOfJoining(LocalDate.of(2026, 12,30));

        when(repository.save(any(Employee.class))).thenReturn(updated);

        Employee result = service.update(1L, dto);

        assertNotNull(dto.toString());
        assertEquals(1L, result.getId());
        assertEquals("Balaji", result.getName());
        assertNotEquals(existing.getDepartmentName(), result.getDepartmentName());
        assertNotEquals(existing.getSalary(), result.getSalary());

        verify(repository, times(1)).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee_Success() {
        Employee existing = new Employee();
        existing.setId(1L);
        existing.setName("Balaji");
        existing.setCompanyName("ABC");
        existing.setDepartmentName("SE");
        existing.setSalary(20000);
        existing.setDateOfJoining(LocalDate.of(2026, 12,30));

        when(repository.existsById(1L)).thenReturn(true);

        boolean result = service.delete(1L);

        assertTrue(result);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteEmployee_NotFound() {
        when(repository.existsById(99L)).thenReturn(false);

        boolean result = service.delete(99L);

        assertFalse(result);
        verify(repository, never()).deleteById(99L);
    }

}
