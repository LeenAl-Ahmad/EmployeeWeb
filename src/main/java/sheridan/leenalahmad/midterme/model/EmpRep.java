package sheridan.leenalahmad.midterme.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

public interface EmpRep extends JpaRepository<Employee, Long> {

    List<Employee> findByTermIgnoreCase(String term);
}

