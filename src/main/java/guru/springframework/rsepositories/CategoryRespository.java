package guru.springframework.rsepositories;

import guru.springframework.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRespository extends JpaRepository<Category, Long>
{
}
