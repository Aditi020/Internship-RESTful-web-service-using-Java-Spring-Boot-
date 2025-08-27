package com.example.restservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.List;

public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeManager employeeManager;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void shouldReturnEmployees() throws Exception {
        // First debug the response structure
        Employees mockEmployees = new Employees(List.of(
                new Employee("1", "John", "Doe", "john.doe@example.com", "Software Engineer"),
                new Employee("2", "Jane", "Smith", "jane.smith@example.com", "Product Manager")
        ));

        when(employeeManager.getEmployees()).thenReturn(mockEmployees);

        // Debug: Print the actual response structure
        String response = mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println("DEBUG - Actual response: " + response);

        // Test with the correct JSON path based on the debug output
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeList").exists())
                .andExpect(jsonPath("$.employeeList[0].employee_id").value("1"))
                .andExpect(jsonPath("$.employeeList[0].first_name").value("John"))
                .andExpect(jsonPath("$.employeeList[1].employee_id").value("2"))
                .andExpect(jsonPath("$.employeeList[1].first_name").value("Jane"));
    }

    @Test
    public void shouldAddEmployee() throws Exception {
        Employees mockEmployees = new Employees(List.of(
                new Employee("1", "John", "Doe", "john.doe@example.com", "Software Engineer")
        ));

        when(employeeManager.getEmployees()).thenReturn(mockEmployees);

        String newEmployeeJson = """
        {
            "employee_id": "5",
            "first_name": "Alice",
            "last_name": "Johnson",
            "email": "alice@example.com",
            "title": "Developer"
        }
        """;

        mockMvc.perform(post("/employees")
                        .contentType("application/json")
                        .content(newEmployeeJson))
                .andExpect(status().isOk());
    }
}