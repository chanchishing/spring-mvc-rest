package guru.springframework.repositories;

import guru.springframework.domain.Category;
import guru.springframework.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor,Long> {
}
