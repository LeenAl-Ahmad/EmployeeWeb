package sheridan.leenalahmad.midterme.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sheridan.leenalahmad.midterme.model.EmpRep;
import sheridan.leenalahmad.midterme.model.Employee;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmpController {
    private final EmpRep empRep;

    public EmpController(EmpRep employeeRepository) {
        this.empRep = employeeRepository;
    }

    // GET all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = empRep.findAll();
        return ResponseEntity.ok(employees);
    }

    // GET single employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = empRep.findById(id);

        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST create new employee
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = empRep.save(employee);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEmployee.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedEmployee);
    }

    // PUT update existing employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employeeDetails) {

        return empRep.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setFirstName(employeeDetails.getFirstName());
                    existingEmployee.setLastName(employeeDetails.getLastName());
                    existingEmployee.setTerm(employeeDetails.getTerm());
                    existingEmployee.setSalary(employeeDetails.getSalary());

                    Employee updatedEmployee = empRep.save(existingEmployee);
                    return ResponseEntity.ok(updatedEmployee);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (empRep.existsById(id)) {
            empRep.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Additional endpoint: Get employees by term
    @GetMapping("/term/{term}")
    public ResponseEntity<List<Employee>> getEmployeesByTerm(@PathVariable String term) {
        List<Employee> employees = empRep.findByTermIgnoreCase(term);

        if (employees.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employees);
    }
}


