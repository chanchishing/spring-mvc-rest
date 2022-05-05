package guru.springframework.services;

import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.api.v1.model.CustomerListDTO;

import java.util.List;

public interface CustomerService {
    CustomerListDTO getAllCustomers();

    CustomerDTO getCustomerById(String id);
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    CustomerDTO saveCustomer(String id, CustomerDTO customerDTO);
}
