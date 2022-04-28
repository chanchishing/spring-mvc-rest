package guru.springframework.repositories;

import guru.springframework.domain.Category;
import guru.springframework.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    public Customer findByFirstname(String firstname);
    public Customer findByLastname(String lastname);
}
