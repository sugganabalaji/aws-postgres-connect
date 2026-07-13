package com.app.awsrdsjavaconnect.config;

import com.app.awsrdsjavaconnect.entity.Employee;
import com.app.awsrdsjavaconnect.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            repository.save(new Employee("Balaji Suggana", 260000.0, "ABC", "IT", LocalDate.of(2016, 06, 27)));
            repository.save(new Employee("Jane Smith", 18000.0, "XYZ", "HR", LocalDate.of(1991, 2, 5)));
            repository.save(new Employee("Ravi Kumar", 20000.0, "TechSoft", "IT", LocalDate.of(1992, 3, 10)));
            repository.save(new Employee("Meena Reddy", 22000.0, "GlobalCorp", "Finance", LocalDate.of(1993, 4, 15)));
            repository.save(new Employee("Alex Johnson", 25000.0, "ABC", "Sales", LocalDate.of(1994, 5, 20)));
            repository.save(new Employee("Sara Lee", 17000.0, "XYZ", "Support", LocalDate.of(1995, 6, 25)));
            repository.save(new Employee("Krishna Rao", 21000.0, "TechSoft", "Finance", LocalDate.of(1996, 7, 30)));
            repository.save(new Employee("Priya Sharma", 23000.0, "GlobalCorp", "Engineering", LocalDate.of(1997, 8, 12)));
            repository.save(new Employee("David Brown", 16000.0, "ABC", "HR", LocalDate.of(1998, 9, 18)));
            repository.save(new Employee("Emily Davis", 24000.0, "XYZ", "Sales", LocalDate.of(1999, 10, 22)));

            repository.save(new Employee("Michael Scott", 26000.0, "TechSoft", "Engineering", LocalDate.of(2000, 11, 5)));
            repository.save(new Employee("Angela Martin", 19000.0, "GlobalCorp", "Finance", LocalDate.of(2001, 12, 9)));
            repository.save(new Employee("Dwight Schrute", 20000.0, "ABC", "Sales", LocalDate.of(2002, 1, 14)));
            repository.save(new Employee("Pam Beesly", 17500.0, "XYZ", "Support", LocalDate.of(2003, 2, 19)));
            repository.save(new Employee("Jim Halpert", 27000.0, "TechSoft", "Engineering", LocalDate.of(2004, 3, 24)));
            repository.save(new Employee("Stanley Hudson", 18500.0, "GlobalCorp", "HR", LocalDate.of(2005, 4, 29)));
            repository.save(new Employee("Kelly Kapoor", 16500.0, "ABC", "Support", LocalDate.of(2006, 5, 3)));
            repository.save(new Employee("Oscar Martinez", 21000.0, "XYZ", "Finance", LocalDate.of(2007, 6, 8)));
            repository.save(new Employee("Kevin Malone", 16000.0, "TechSoft", "Finance", LocalDate.of(2008, 7, 13)));
            repository.save(new Employee("Meredith Palmer", 15500.0, "GlobalCorp", "HR", LocalDate.of(2009, 8, 18)));

            repository.save(new Employee("Ryan Howard", 22000.0, "ABC", "Sales", LocalDate.of(2010, 9, 23)));
            repository.save(new Employee("Toby Flenderson", 18000.0, "XYZ", "HR", LocalDate.of(2011, 10, 28)));
            repository.save(new Employee("Holly Flax", 24000.0, "TechSoft", "HR", LocalDate.of(2012, 11, 2)));
            repository.save(new Employee("Andy Bernard", 20000.0, "GlobalCorp", "Sales", LocalDate.of(2013, 12, 7)));
            repository.save(new Employee("Erin Hannon", 17000.0, "ABC", "Support", LocalDate.of(2014, 1, 12)));
            repository.save(new Employee("Charles Miner", 28000.0, "XYZ", "Engineering", LocalDate.of(2015, 2, 17)));
            repository.save(new Employee("Jan Levinson", 30000.0, "TechSoft", "Finance", LocalDate.of(2016, 3, 22)));
            repository.save(new Employee("Darryl Philbin", 23000.0, "GlobalCorp", "Support", LocalDate.of(2017, 4, 27)));
            repository.save(new Employee("Roy Anderson", 19000.0, "ABC", "Engineering", LocalDate.of(2018, 5, 2)));
            repository.save(new Employee("Carol Stills", 21000.0, "XYZ", "Finance", LocalDate.of(2019, 6, 7)));

            repository.save(new Employee("Josh Porter", 26000.0, "TechSoft", "Engineering", LocalDate.of(2020, 7, 12)));
            repository.save(new Employee("Karen Filippelli", 22000.0, "GlobalCorp", "Sales", LocalDate.of(2021, 8, 17)));
            repository.save(new Employee("Nellie Bertram", 23000.0, "ABC", "HR", LocalDate.of(2022, 9, 22)));
            repository.save(new Employee("Clark Green", 18000.0, "XYZ", "Engineering", LocalDate.of(2023, 10, 27)));
            repository.save(new Employee("Pete Miller", 20000.0, "TechSoft", "Support", LocalDate.of(2024, 11, 1)));
            repository.save(new Employee("Hank Tate", 15000.0, "GlobalCorp", "Support", LocalDate.of(2025, 12, 6)));
            repository.save(new Employee("Madge Madsen", 16000.0, "ABC", "HR", LocalDate.of(2026, 1, 11)));
            repository.save(new Employee("Lonny Collins", 21000.0, "XYZ", "Support", LocalDate.of(2026, 2, 16)));
            repository.save(new Employee("Glenn", 17000.0, "TechSoft", "Engineering", LocalDate.of(2026, 3, 21)));
            repository.save(new Employee("Jessica", 19000.0, "GlobalCorp", "Finance", LocalDate.of(2026, 4, 26)));

            repository.save(new Employee("Mark", 20000.0, "ABC", "Sales", LocalDate.of(2026, 5, 1)));
            repository.save(new Employee("Sophia", 25000.0, "XYZ", "Engineering", LocalDate.of(2026, 6, 6)));
            repository.save(new Employee("Daniel", 18000.0, "TechSoft", "HR", LocalDate.of(2026, 7, 11)));
            repository.save(new Employee("Olivia", 22000.0, "GlobalCorp", "Finance", LocalDate.of(2026, 8, 16)));
            repository.save(new Employee("Ethan", 21000.0, "ABC", "Sales", LocalDate.of(2026, 9, 21)));
            repository.save(new Employee("Ava", 17000.0, "XYZ", "Support", LocalDate.of(2026, 10, 26)));
            repository.save(new Employee("Liam", 26000.0, "TechSoft", "Engineering", LocalDate.of(2026, 11, 1)));
            repository.save(new Employee("Isabella", 23000.0, "GlobalCorp", "Finance", LocalDate.of(2026, 12, 6)));
            repository.save(new Employee("Noah", 20000.0, "ABC", "Sales", LocalDate.of(2026, 1, 11)));
            repository.save(new Employee("Mia", 18000.0, "XYZ", "Support", LocalDate.of(2026, 2, 16)));
        };
    }

}
