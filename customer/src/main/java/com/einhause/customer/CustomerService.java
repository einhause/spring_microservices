package com.einhause.customer;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // check if email is valid

        customerRepository.saveAndFlush(customer);

        // checking if customer is fraud
        FraudCheckHistoryResponse fraudCheckResponse = restTemplate.getForObject(
                "http:localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckHistoryResponse.class,
                customer.getId()
        );git a

        if (fraudCheckResponse.customerIsFraud()) {
            throw new IllegalStateException("Illegal customer");
        }

        // send notification
    }
}
