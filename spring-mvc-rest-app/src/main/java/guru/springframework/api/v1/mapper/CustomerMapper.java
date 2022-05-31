package guru.springframework.api.v1.mapper;


import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;



@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "customer_url", expression = "java(guru.springframework.api.v1.model.Constant.API_V_1_CUSTOMERS_URL+\"/\"+customer.getId())")
    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}

