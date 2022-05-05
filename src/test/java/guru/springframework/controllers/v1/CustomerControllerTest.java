package guru.springframework.controllers.v1;

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.api.v1.model.CustomerListDTO;
import guru.springframework.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static guru.springframework.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

        CustomerListDTO customerDTOList= new CustomerListDTO(Arrays.asList(cust1DTO,cust2DTO));

        when(mockCustomerService.getAllCustomers()).thenReturn(customerDTOList);

        customerController.getAllCustomers();

        mockMvc.perform(get("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)))
                .andExpect(jsonPath("$.customers[0].id",equalTo(cust1ID.intValue())));

    }

    @Test
    void getCustomerById() throws Exception {
        CustomerDTO cust1DTO=new CustomerDTO();
        Long cust1ID=1L;
        String cust1Firstname="Customer 1 First name";
        cust1DTO.setId(cust1ID);
        cust1DTO.setFirstname(cust1Firstname);


        when(mockCustomerService.getCustomerById(anyLong())).thenReturn(cust1DTO);

        mockMvc.perform(get("/api/v1/customers/"+cust1ID.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(cust1ID.intValue())))
                .andExpect(jsonPath("$.firstname", equalTo(cust1Firstname)));
    }

    @Test
    void createNewCustomer() throws Exception {
        Long cust1ID=1L;
        String cust1Firstname="Customer 1 First name";

        CustomerDTO custDTOToSave=new CustomerDTO();
        custDTOToSave.setId(cust1ID);
        custDTOToSave.setFirstname(cust1Firstname);

        CustomerDTO savedCustDTO=new CustomerDTO();
        savedCustDTO.setId(cust1ID);
        savedCustDTO.setFirstname(cust1Firstname);


        when(mockCustomerService.createNewCustomer(any(CustomerDTO.class))).thenReturn(savedCustDTO);

        mockMvc.perform(post("/api/v1/customers/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(custDTOToSave)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id",equalTo(cust1ID.intValue())))
                        .andExpect(jsonPath("$.firstname",equalTo(cust1Firstname)));

    }

    @Test
    void saveCustomer() throws Exception {
        Long cust1ID=1L;
        String cust1Firstname="Customer 1 First name";

        CustomerDTO custDTOToSave=new CustomerDTO();
        custDTOToSave.setId(cust1ID);
        custDTOToSave.setFirstname(cust1Firstname);

        CustomerDTO savedCustDTO=new CustomerDTO();
        savedCustDTO.setId(cust1ID);
        savedCustDTO.setFirstname(cust1Firstname);


        when(mockCustomerService.saveCustomer(anyLong(),any(CustomerDTO.class))).thenReturn(savedCustDTO);

        mockMvc.perform(put("/api/v1/customers/"+ cust1ID.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(custDTOToSave))
                )

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",equalTo(cust1ID.intValue())))
                .andExpect(jsonPath("$.firstname",equalTo(cust1Firstname)));

    }


}