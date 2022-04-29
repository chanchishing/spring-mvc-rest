package guru.springframework.services;

import guru.springframework.api.v1.mapper.CategoryMapper;
import guru.springframework.api.v1.mapper.CustomerMapper;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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

        List<CustomerDTO> customerDTOList=customerService.getAllCustomers();
        assertEquals(customerList.size(),customerDTOList.size());
    }

    @Test
    void getCustomerByFirstname() {
        Customer customer1=new Customer();
        Customer customer2=new Customer();
        Long testID1=7L;
        Long testID2=8L;
        customer1.setId(testID1);
        customer2.setId(testID2);
        List<Customer> customerList=new ArrayList<Customer>(Arrays.asList(customer1,customer2));

        when(mockCustomerRepository.findByFirstname(anyString())).thenReturn(customerList);

        List<CustomerDTO> customerDTOList=customerService.getCustomerByFirstname(anyString());
        assertEquals(customerList.size(),customerDTOList.size());
        assertEquals(
                "/shop/customer/"+testID2.toString(),
                customerDTOList.stream().filter(customerDTO -> customerDTO.getId()==testID2).collect(Collectors.toList())
                        .stream().findFirst().get().getCustomer_url()
        );

    }

    @Test
    void getCustomerByLastname() {
        Customer customer1=new Customer();
        Customer customer2=new Customer();
        Long testID1=7L;
        Long testID2=8L;
        customer1.setId(testID1);
        customer2.setId(testID2);
        List<Customer> customerList=new ArrayList<Customer>(Arrays.asList(customer1,customer2));

        when(mockCustomerRepository.findByLastname(anyString())).thenReturn(customerList);

        List<CustomerDTO> customerDTOList=customerService.getCustomerByLastname(anyString());
        assertEquals(customerList.size(),customerDTOList.size());
        assertEquals(
                "/shop/customer/"+testID1.toString(),
                customerDTOList.stream().filter(customerDTO -> customerDTO.getId()==testID1).collect(Collectors.toList())
                        .stream().findFirst().get().getCustomer_url()
        );
    }
}