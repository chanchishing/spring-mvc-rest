package guru.springframework.controllers.v1;

import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    private AutoCloseable closeable;

    CustomerController customerController;

    @Mock
    CustomerService mockCustomerService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        customerController = new CustomerController(mockCustomerService);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                //.setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }


    @Test
    void getAllCustomers() throws Exception {
        CustomerDTO cust1DTO=new CustomerDTO();
        CustomerDTO cust2DTO=new CustomerDTO();
        Long cust1ID=1L;
        Long cust2ID=2L;
        String cust1Firstname="Customer 1 First name";
        String cust2Firstname="Customer 2 First name";
        cust1DTO.setId(cust1ID);
        cust1DTO.setFirstname(cust1Firstname);
        cust2DTO.setId(cust2ID);
        cust2DTO.setFirstname(cust2Firstname);

        List<CustomerDTO> customerDTOList= Arrays.asList(cust1DTO,cust2DTO);

        when(mockCustomerService.getAllCustomers()).thenReturn(customerDTOList);

        customerController.getAllCustomers();

        mockMvc.perform(get("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        ;
    }

    @Test
    void getCustomerById() throws Exception {
        CustomerDTO cust1DTO=new CustomerDTO();
        Long cust1ID=1L;
        String cust1Firstname="Customer 1 First name";
        cust1DTO.setId(cust1ID);
        cust1DTO.setFirstname(cust1Firstname);


        when(mockCustomerService.getCustomerById(anyString())).thenReturn(cust1DTO);

        mockMvc.perform(get("/api/v1/customers/anyString").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(cust1ID.intValue())))
                .andExpect(jsonPath("$.firstname", equalTo(cust1Firstname)));
    }
}