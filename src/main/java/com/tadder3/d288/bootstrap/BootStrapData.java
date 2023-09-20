package com.tadder3.d288.bootstrap;

import com.tadder3.d288.dao.CustomerRepository;
import com.tadder3.d288.dao.DivisionRepository;
import com.tadder3.d288.entities.Country;
import com.tadder3.d288.entities.Customer;
import com.tadder3.d288.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Section I requires that the data is not overwritten after every run; multiple runs create multiple instances
        // customerRepository.deleteAll(); // Can be used to delete data between runs, but leaving commented out

        Division testDivision = new Division();
        testDivision.setCountry_id(Long.valueOf("1") );
        testDivision.setDivision_name("Testopia");

        divisionRepository.save(testDivision);

        Customer customer1 = new Customer();
        customer1.setFirstName("Yelredda");
        customer1.setLastName("Yelredda");
        customer1.setAddress("111 Main Street");
        customer1.setPostal_code("11111");
        customer1.setPhone("111-111-1111");
        customer1.setDivision(testDivision);
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Another");
        customer2.setLastName("Customer");
        customer2.setAddress("222 Main Street");
        customer2.setPostal_code("22222");
        customer2.setPhone("222-222-2222");
        customer2.setDivision(testDivision);
        customerRepository.save(customer2);

        Customer customer3 = new Customer();
        customer3.setFirstName("Some");
        customer3.setLastName("Customer");
        customer3.setAddress("333 Another Street");
        customer3.setPostal_code("33333");
        customer3.setPhone("333-333-3333");
        customer3.setDivision(testDivision);
        customerRepository.save(customer3);

        Customer customer4 = new Customer();
        customer4.setFirstName("This");
        customer4.setLastName("Customer");
        customer4.setAddress("444 Main Street");
        customer4.setPostal_code("44444");
        customer4.setPhone("444-444-4444");
        customer4.setDivision(testDivision);
        customerRepository.save(customer4);

        Customer customer5 = new Customer();
        customer5.setFirstName("That");
        customer5.setLastName("Customer");
        customer5.setAddress("555 Main Street");
        customer5.setPostal_code("55555");
        customer5.setPhone("555-555-5555");
        customer5.setDivision(testDivision);
        customerRepository.save(customer5);

    }
}
