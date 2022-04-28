package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    CategoryRepository categoryRepository;
    CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    private void initCategory(){
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

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Category Loaded = " + categoryRepository.count() );

    }

    private void initCustomer(){
        Customer jamesBond = new Customer();
        jamesBond.setFirstname("James");
        jamesBond.setLastname("Bond");

        Customer jackRyan = new Customer();
        jackRyan.setFirstname("Jack");
        jackRyan.setLastname("Ryan");

        Customer jasonBourne = new Customer();
        jasonBourne.setFirstname("Jason");
        jasonBourne.setLastname("Bourne");

        Customer jackBauer = new Customer();
        jackBauer.setFirstname("Jack");
        jackBauer.setLastname("Bauer");


        customerRepository.save(jamesBond);
        customerRepository.save(jackRyan);
        customerRepository.save(jasonBourne);
        customerRepository.save(jackBauer);

        System.out.println("Customer Loaded = " + customerRepository.count() );

    }


    @Override
    public void run(String... args) throws Exception {
        initCategory();
        initCustomer();
    }
}
