package guru.springframework.services;

import guru.springframework.api.v1.mapper.CustomerMapper;
import guru.springframework.model.CustomerDTO;
import guru.springframework.model.CustomerListDTO;
import guru.springframework.domain.Customer;
import guru.springframework.exceptions.ResourceNotFoundException;
import guru.springframework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        //to cope with no set() method for XJC generated CustomerListDTO.customers class
        //return new CustomerListDTO(customerRepository.findAll().stream().map(customerMapper::customerToCustomerDTO).collect(Collectors.toList()));

        CustomerListDTO customerListDTO = new CustomerListDTO();
        customerRepository
                .findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .forEach(customerDTO -> {customerListDTO.getCustomers().add(customerDTO);});
        return customerListDTO;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerMapper.customerToCustomerDTO(customerRepository.findById(Long.valueOf(id)).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.customerToCustomerDTO(savedCustomer);


    }

    @Override
    public CustomerDTO saveCustomer(Long id, CustomerDTO customerDTO) {

        customerDTO.setId(id);
        Customer savedCustomer= customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO));

        return customerMapper.customerToCustomerDTO(savedCustomer);

    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {

            if(customerDTO.getFirstname() != null){
                customer.setFirstname(customerDTO.getFirstname());
            }

            if(customerDTO.getLastname() != null){
                customer.setLastname(customerDTO.getLastname());
            }

            return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomer(Long id) {

        customerRepository.findById(id).map(customer -> {
            customerRepository.deleteById(id);
            return Optional.empty();
        }).orElseThrow(ResourceNotFoundException::new);
    }


}
