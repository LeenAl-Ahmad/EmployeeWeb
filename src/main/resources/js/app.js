document.addEventListener('DOMContentLoaded', function() {
    const loadBtn = document.getElementById('load-btn');
    const addBtn = document.getElementById('add-btn');
    const employeeList = document.getElementById('employee-list');

    loadBtn.addEventListener('click', loadEmployees);
    addBtn.addEventListener('click', addSampleEmployee);

    async function loadEmployees() {
        try {
            const response = await fetch('http://localhost:8080/employees');
            if (!response.ok) throw new Error('Network response was not ok');

            const data = await response.json();
            displayEmployees(data._embedded.employees);
        } catch (error) {
            console.error('Error loading employees:', error);
            employeeList.innerHTML = '<p class="error">Error loading employees. Please try again.</p>';
        }
    }

    async function addSampleEmployee() {
        try {
            const response = await fetch('http://localhost:8080/employees', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    firstName: 'New',
                    lastName: 'Employee',
                    term: 'CONTRACT',
                    salary: 55000.00
                })
            });

            if (!response.ok) throw new Error('Failed to add employee');

            loadEmployees(); // Refresh the list
        } catch (error) {
            console.error('Error adding employee:', error);
            alert('Failed to add employee');
        }
    }

    function displayEmployees(employees) {
        if (!employees || employees.length === 0) {
            employeeList.innerHTML = '<p>No employees found.</p>';
            return;
        }

        employeeList.innerHTML = employees.map(employee => `
            <div class="employee-card">
                <h3>${employee.firstName} ${employee.lastName}</h3>
                <p><strong>ID:</strong> ${employee.id}</p>
                <p><strong>Term:</strong> 
                    <span class="term term-${employee.term.toLowerCase()}">${employee.term.replace('_', ' ')}</span>
                </p>
                <p><strong>Salary:</strong> <span class="salary">$${employee.salary.toLocaleString()}</span></p>
            </div>
        `).join('');
    }
});