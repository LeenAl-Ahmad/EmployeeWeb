package sheridan.leenalahmad.midterme.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String term; // "FULL_TIME", "PART_TIME", "CONTRACT"
    private Double salary;

    public Employee(String firstName, String lastName, String term, Double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.term = term;
        this.salary = salary;
    }
}



