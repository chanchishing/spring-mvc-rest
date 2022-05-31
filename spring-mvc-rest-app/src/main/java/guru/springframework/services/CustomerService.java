package guru.springframework.services;

import guru.springframework.model.CustomerDTO;
import guru.springframework.model.CustomerListDTO;

public interface CustomerService {
    CustomerListDTO getAllCustomers();

    CustomerDTO getCustomerById(Long id);
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    CustomerDTO saveCustomer(Long id, CustomerDTO customerDTO);
    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
    void deleteCustomer(Long id);

}
