package guru.springframework.services;

import guru.springframework.api.v1.mapper.CustomerMapper;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerMapper customerMapper,CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::customerToCustomerDTO).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getCustomerByFirstname(String firstname) {
        return customerRepository.findByFirstname(firstname).stream().map(customerMapper::customerToCustomerDTO).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getCustomerByLastname(String lastname) {
        return customerRepository.findByLastname(lastname).stream().map(customerMapper::customerToCustomerDTO).collect(Collectors.toList());
    }
}
