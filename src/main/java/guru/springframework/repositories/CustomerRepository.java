package guru.springframework.repositories;

import guru.springframework.domain.Category;
import guru.springframework.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{
}
