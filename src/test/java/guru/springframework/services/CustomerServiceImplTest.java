package guru.springframework.services;

import guru.springframework.api.v1.mapper.CategoryMapper;
import guru.springframework.api.v1.mapper.CustomerMapper;
import guru.springframework.api.v1.model.Constant;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.api.v1.model.CustomerListDTO;
import guru.springframework.domain.Customer;
import guru.springframework.exceptions.ResourceNotFoundException;
import guru.springframework.repositories.CustomerRepository;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {
    AutoCloseable closeable;

    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository mockCustomerRepository;

    @BeforeEach
    void setUp() {
        closeable= MockitoAnnotations.openMocks(this);
        customerService= new CustomerServiceImpl(CustomerMapper.INSTANCE,mockCustomerRepository);
    }

    @Test
    void getAllCustomers() {
        Customer customer1=new Customer();
        Customer customer2=new Customer();
        List<Customer> customerList=new ArrayList<Customer>(Arrays.asList(customer1,customer2));

        when(mockCustomerRepository.findAll()).thenReturn(customerList);

        CustomerListDTO customerDTOList=customerService.getAllCustomers();
        assertEquals(customerList.size(),customerDTOList.getCustomers().size());
    }

    @Test
    void getCustomerById() {
        Customer customer1=new Customer();
        Long testID1=7L;
        String testFirstName="first name";
        customer1.setId(testID1);
        customer1.setFirstname(testFirstName);
        Optional<Customer> customerOptional=Optional.of(customer1);

        when(mockCustomerRepository.findById(anyLong())).thenReturn(customerOptional);

        CustomerDTO customerDTO=customerService.getCustomerById(testID1);

        verify(mockCustomerRepository,times(1)).findById(anyLong());

        assertEquals(testID1,customerDTO.getId());
        assertEquals(testFirstName,customerDTO.getFirstname());
        assertEquals(Constant.API_V_1_CUSTOMERS_URL+"/"+testID1.toString(),customerDTO.getCustomer_url());

    }

    @Test
    void createNewCustomer() {
        Customer customer1=new Customer();
        Long testID1=7L;
        String testFirstName="first name";
        customer1.setId(testID1);
        customer1.setFirstname(testFirstName);

        CustomerDTO customerDTO1=new CustomerDTO();
        customerDTO1.setId(testID1);
        customerDTO1.setFirstname(testFirstName);

        when(mockCustomerRepository.save(any(Customer.class))).thenReturn(customer1);

        CustomerDTO savedCustomerDTO=customerService.createNewCustomer(customerDTO1);

        verify(mockCustomerRepository,times(1)).save(any(Customer.class));
        assertEquals(testID1,savedCustomerDTO.getId());
        assertEquals(testFirstName,savedCustomerDTO.getFirstname());
        assertEquals(Constant.API_V_1_CUSTOMERS_URL+"/"+testID1.toString(),savedCustomerDTO.getCustomer_url());

    }

    @Test
    void saveCustomer() {
        Customer customer1=new Customer();
        Long testID1=7L;
        String testFirstName="first name";
        customer1.setId(testID1);
        customer1.setFirstname(testFirstName);

        CustomerDTO customerDTO1=new CustomerDTO();
        customerDTO1.setId(testID1);
        customerDTO1.setFirstname(testFirstName);


        when(mockCustomerRepository.save(any(Customer.class))).thenReturn(customer1);

        CustomerDTO savedCustomerDTO=customerService.saveCustomer(testID1,customerDTO1);
        verify(mockCustomerRepository,times(1)).save(any(Customer.class));
        assertEquals(testID1,savedCustomerDTO.getId());
        assertEquals(testFirstName,savedCustomerDTO.getFirstname());
        assertEquals(Constant.API_V_1_CUSTOMERS_URL+"/"+testID1.toString(),savedCustomerDTO.getCustomer_url());

    }

    @Test
    void deleteCustomer() {
       Long testID1=7L;

       assertThrows(ResourceNotFoundException.class,()->{customerService.deleteCustomer(testID1);});

       verify(mockCustomerRepository,times(1)).findById(testID1);

    }
}