package sheridan.leenalahmad.midterme.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sheridan.leenalahmad.midterme.model.EmpRep;
import sheridan.leenalahmad.midterme.model.Employee;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initDatabase(EmpRep repository) {
        return args -> {
            repository.save(new Employee("John", "Doe", "FULL_TIME", 75000.00));
            repository.save(new Employee("Jane", "Smith", "PART_TIME", 45000.00));
            repository.save(new Employee("Mike", "Johnson", "CONTRACT", 60000.00));
            repository.save(new Employee("Sarah", "Williams", "FULL_TIME", 80000.00));
        };
    }
}

