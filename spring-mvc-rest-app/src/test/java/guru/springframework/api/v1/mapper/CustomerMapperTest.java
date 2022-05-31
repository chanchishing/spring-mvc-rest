package guru.springframework.api.v1.mapper;

import guru.springframework.api.v1.model.Constant;
import guru.springframework.model.CustomerDTO;
import guru.springframework.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    private final String FIRSTNAME ="first name";
    private final String LASTNAME ="last name";
    private final Long ID=99L;
    

    @Test
    void customerToCustomerDTO() {
        //given
        Customer customer = new Customer();
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);
        customer.setId(ID);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(Long.valueOf(ID), customerDTO.getId());
        assertEquals(FIRSTNAME, customerDTO.getFirstname());
        assertEquals(LASTNAME, customerDTO.getLastname());
        assertEquals(Constant.API_V_1_CUSTOMERS_URL+"/"+ID.toString(),customerDTO.getCustomer_url());
        
    }

    @Test
    void customerDTOToCustomer() {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(FIRSTNAME);
        customerDTO.setLastname(LASTNAME);
        customerDTO.setId(ID);

        //when
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        //then
        assertEquals(Long.valueOf(ID), customer.getId());
        assertEquals(FIRSTNAME, customer.getFirstname());
        assertEquals(LASTNAME, customer.getLastname());

    }

}