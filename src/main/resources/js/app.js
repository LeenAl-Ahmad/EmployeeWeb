async function loadEmployees() {
    try {
        const response = await fetch('http://localhost:8080/api/employees');
        if (!response.ok) throw new Error('Network response was not ok');

        const employees = await response.json(); // Direct array, no _embedded
        displayEmployees(employees); // Pass the array directly
    } catch (error) {
        console.error('Error loading employees:', error);
        employeeList.innerHTML = '<p class="error">Error loading employees. Please try again.</p>';
    }
}