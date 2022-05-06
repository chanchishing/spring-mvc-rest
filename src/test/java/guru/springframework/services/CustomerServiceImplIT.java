package guru.springframework.services;

import guru.springframework.api.v1.mapper.CustomerMapper;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.bootstrap.Bootstrap;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.CustomerRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
public class CustomerServiceImplIT {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CustomerRepository customerRepository;

    CustomerServiceImpl customerService;

    @BeforeAll
    void setUp() {
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE,customerRepository);
    }


    @BeforeEach
    void setUpForEachTest() throws Exception {
        Bootstrap bootstrap = new Bootstrap(categoryRepository,customerRepository);
        bootstrap.run();
    }

    private Customer getFirstCustomer(){
        return customerRepository.findAll().stream().findFirst().orElseThrow();
    }

    @Test
    void patchCustomerLastname() {
        Customer testCustomer = getFirstCustomer();
        Long testCustId=testCustomer.getId();
        CustomerDTO customerDTO= CustomerMapper.INSTANCE.customerToCustomerDTO(testCustomer);

        CustomerDTO customerDTOToBe= CustomerMapper.INSTANCE.customerToCustomerDTO(testCustomer);
        customerDTOToBe.setLastname("patched Lastname");
        customerDTOToBe.setFirstname(null);

        CustomerDTO patchedCustomerDTO=customerService.patchCustomer(testCustId,customerDTOToBe);

        assertEquals(testCustId,patchedCustomerDTO.getId());
        assertEquals(customerDTO.getFirstname(),patchedCustomerDTO.getFirstname());
        //assertEquals(customerDTOToBe.getLastname(),patchedCustomerDTO.getLastname());
        assertThat(customerDTOToBe.getLastname(),equalTo(patchedCustomerDTO.getLastname()));
    }

    @Test
    void patchCustomerFirstname() {
        Customer testCustomer = getFirstCustomer();
        Long testCustId=testCustomer.getId();
        CustomerDTO customerDTO= CustomerMapper.INSTANCE.customerToCustomerDTO(testCustomer);

        CustomerDTO customerDTOToBe= CustomerMapper.INSTANCE.customerToCustomerDTO(testCustomer);
        customerDTOToBe.setFirstname("patched Firstname");
        customerDTOToBe.setLastname(null);

        CustomerDTO patchedCustomerDTO=customerService.patchCustomer(testCustId,customerDTOToBe);

        assertEquals(testCustId,patchedCustomerDTO.getId());
        assertEquals(customerDTOToBe.getFirstname(),patchedCustomerDTO.getFirstname());
        //assertEquals(customerDTOToBe.getLastname(),patchedCustomerDTO.getLastname());
        assertThat(customerDTO.getLastname(),equalTo(patchedCustomerDTO.getLastname()));
    }

}
