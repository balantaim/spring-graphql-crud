package com.martinatanasov.springgraphql.controllers;

import com.martinatanasov.springgraphql.entity.Customer;
import com.martinatanasov.springgraphql.entity.Payment;
import com.martinatanasov.springgraphql.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @QueryMapping
    public Iterable<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @QueryMapping
    public Customer findCustomer(@Argument final Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Customer createCustomer(@Argument String userName,
                                   @Argument String fullName,
                                   @Argument String email,
                                   @Argument String gender,
                                   @Argument String address) {

        Customer customer = Customer.builder()
                .userName(userName)
                .fullName(fullName)
                .email(email)
                .gender(gender)
                .address(address)
                .enabled(true)
                .build();

        return customerRepository.save(customer);
    }

    @MutationMapping
    public Customer addPayment(@Argument final Integer id, @Argument Double currency) {
        BigDecimal bigDecimal = BigDecimal.valueOf(currency);
        //Get the first user
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {
            //convert dto to payment
            Payment payment = Payment.builder()
                    .currency(bigDecimal)
                    .customer(customer.get())
                    .created(new Timestamp(System.currentTimeMillis()))
                    .finalized(new Timestamp(System.currentTimeMillis()))
                    .build();
            //add the payment to the customer
            Set<Payment> paymentSet = new HashSet<>();
            paymentSet.add(payment);
            Customer customerResult = customer.get();
            customerResult.setPayments(paymentSet);
            //save the object
            return customerRepository.save(customerResult);
        }
        return null;
    }

    @MutationMapping
    public Customer updateCustomer(@Argument Integer id,
                                   @Argument String userName,
                                   @Argument String fullName,
                                   @Argument String email,
                                   @Argument String gender,
                                   @Argument String address) {

        Optional<Customer> existCustomer = Optional.ofNullable(customerRepository.findById(id).orElse(null));
        if (!existCustomer.isPresent()) {
            throw new RuntimeException("Customer not found!");
        }
        Customer customer = Customer.builder()
                .id(id)
                .userName(userName)
                .fullName(fullName)
                .email(email)
                .enabled(true)
                .gender(gender)
                .address(address)
                .build();
        customerRepository.save(customer);
        return customer;
    }

    @MutationMapping
    public Customer deleteCustomer(@Argument final Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            customerRepository.deleteById(id);
        }
        return customer.orElse(null);
    }


}
