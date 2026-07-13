package com.app.awsrdsjavaconnect.controller;

import com.app.awsrdsjavaconnect.dto.EmployeeDTO;
import com.app.awsrdsjavaconnect.entity.Employee;
import com.app.awsrdsjavaconnect.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService service;

    @Value("${spring.profiles.active}")
    private String dbName;

    @Test
    void testSayOk() throws Exception {
        mockMvc.perform(get("/api/employees/ok"))
                .andExpect(status().isOk())
                .andExpect(content().string("Ok.. :)"));
    }

    @Test
    void testFindAllEmployees_Success() throws Exception {
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
        when(service.findAll()).thenReturn(employees);

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Balaji"))
                .andExpect(jsonPath("$[1].departmentName").value("HR"));
    }

    @Test
    void testFindAllEmployees_EmptyList() throws Exception {
        when(service.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(content().string("Employees not saved in " + dbName));
    }

    @Test
    void testFindById_Success() throws Exception {
        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName("Balaji");
        emp.setCompanyName("ABC");
        emp.setDepartmentName("Dev");
        emp.setSalary(50000);
        emp.setDateOfJoining(LocalDate.of(2026, 12,30));

        when(service.findById(1L)).thenReturn(Optional.of(emp));

        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Balaji"))
                .andExpect(jsonPath("$.companyName").value("ABC"))
                .andExpect(jsonPath("$.departmentName").value("Dev"));
    }

    @Test
    void testFindById_NotFound() throws Exception {
        when(service.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/employees/99"))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee not found with id: 99 in " + dbName));
    }

    @Test
    void testSaveEmployee_Success() throws Exception {
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

        when(service.save(any(EmployeeDTO.class))).thenReturn(saved);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Balaji\",\"companyName\":\"ABC\",\"departmentName\":\"Dev\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Balaji"))
                .andExpect(jsonPath("$.companyName").value("ABC"))
                .andExpect(jsonPath("$.departmentName").value("Dev"));
    }

    @Test
    void testUpdateEmployee_Success() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Balaji");
        dto.setCompanyName("ABC New");
        dto.setDepartmentName("Dev");
        dto.setSalary(60000);
        dto.setDateOfJoining(LocalDate.of(2026, 12,30));

        Employee updated = new Employee();
        updated.setId(1L);
        updated.setName("Balaji");
        updated.setCompanyName("ABC New");
        updated.setDepartmentName("Dev");
        updated.setSalary(60000);
        updated.setDateOfJoining(LocalDate.of(2026, 12,30));

        when(service.findById(1L)).thenReturn(Optional.of(updated));
        when(service.update(eq(1L), any(EmployeeDTO.class))).thenReturn(updated);

        mockMvc.perform(put("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Balaji\",\"companyName\":\"ABC New\",\"departmentName\":\"Dev\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Balaji"))
                .andExpect(jsonPath("$.companyName").value("ABC New"))
                .andExpect(jsonPath("$.departmentName").value("Dev"));
    }

    @Test
    void testUpdateEmployee_NotFound() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Balaji");
        dto.setCompanyName("ABC New");
        dto.setDepartmentName("Dev");
        dto.setSalary(60000);
        dto.setDateOfJoining(LocalDate.of(2026, 12,30));

        when(service.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/employees/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Balaji\",\"companyName\":\"ABC New\",\"departmentName\":\"Dev\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee with id: 99 not found in " + dbName));
    }




    @Test
    void testDeleteEmployee_Success() throws Exception {
        when(service.delete(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee with id: 1 successfully deleted"));
    }

    @Test
    void testDeleteEmployee_NotFound() throws Exception {
        when(service.delete(99L)).thenReturn(false);

        mockMvc.perform(delete("/api/employees/99"))
                .andExpect(status().isOk()) // your controller returns 200 even for not found
                .andExpect(content().string("Employee with id: 99 not found in " +  dbName));
    }
}
