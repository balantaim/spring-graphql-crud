package com.martinatanasov.springgraphql.bootstrap;

import com.martinatanasov.springgraphql.entity.Customer;
import com.martinatanasov.springgraphql.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class Bootstrap implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadFakeCustomer();
    }


    private void loadFakeCustomer() {
        Customer customer = Customer.builder()
                .userName("ivan")
                .fullName("Ivan Ivanov")
                .email("test@abv.bg")
                .address("Bulgaria, Sofia")
                .gender("male")
                .enabled(true)
                .build();

        customerRepository.save(customer);
        log.info("\t Test Customer is created!");
    }

}
