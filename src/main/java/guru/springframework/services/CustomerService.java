package guru.springframework.services;

import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(String id);
    //List<CustomerDTO> getCustomerByFirstname(String firstname);
    //List<CustomerDTO> getCustomerByLastname(String lastname);
}
