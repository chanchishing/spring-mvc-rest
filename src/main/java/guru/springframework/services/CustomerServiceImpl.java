package guru.springframework.services;

import guru.springframework.api.v1.mapper.CustomerMapper;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.api.v1.model.CustomerListDTO;
import guru.springframework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

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
    public CustomerListDTO getAllCustomers() {
        return new CustomerListDTO(customerRepository.findAll().stream().map(customerMapper::customerToCustomerDTO).collect(Collectors.toList()));
    }

    @Override
    public CustomerDTO getCustomerById(String id) {
        return customerMapper.customerToCustomerDTO(customerRepository.findById(Long.valueOf(id)).orElseThrow());
    }


}
