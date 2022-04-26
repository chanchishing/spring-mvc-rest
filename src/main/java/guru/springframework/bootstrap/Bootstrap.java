package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.rsepositories.CategoryRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    CategoryRespository categoryRespository;

    public Bootstrap(CategoryRespository categoryRespository) {
        this.categoryRespository = categoryRespository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRespository.save(fruits);
        categoryRespository.save(dried);
        categoryRespository.save(fresh);
        categoryRespository.save(exotic);
        categoryRespository.save(nuts);


        System.out.println("Data Loaded = " + categoryRespository.count() );

    }
}
