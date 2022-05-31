package guru.springframework.services;

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.api.v1.model.CustomerListDTO;

public interface CustomerService {
    CustomerListDTO getAllCustomers();

    CustomerDTO getCustomerById(Long id);
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    CustomerDTO saveCustomer(Long id, CustomerDTO customerDTO);
    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
    void deleteCustomer(Long id);

}
