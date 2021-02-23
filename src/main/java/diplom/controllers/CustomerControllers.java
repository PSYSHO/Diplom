package diplom.controllers;

import diplom.entity.Customer;
import diplom.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/customers")
public class CustomerControllers {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public Iterable<Customer> getAll() {
        return customerRepository.findAll(Sort.by("firstName").ascending());
    }
    @GetMapping("{Id}")
    public Optional<Customer> getById(@PathVariable("Id") Long id){
        return customerRepository.findById(id);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer Customer){
        return customerRepository.save(Customer);
    }

    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){
        customerRepository.findById(id).map(customer1 -> {
            customer1.setAddress(customer.getAddress());
            customer1.setCity(customer.getCity());
            customer1.setCountry(customer.getCountry());
            customer1.setEmailAddress(customer.getEmailAddress());
            customer1.setFirstName(customer.getFirstName());
            customer1.setLastName(customer.getLastName());
            customer1.setPhoneNumber(customer.getPhoneNumber());
            customer1.setComment(customer.getComment());
            return customerRepository.save(customer1);
        });
    }

    @DeleteMapping("/{Id}")
    public void deleteCustomer(@PathVariable("Id") Customer customer){
        customerRepository.delete(customer);
    }
}
