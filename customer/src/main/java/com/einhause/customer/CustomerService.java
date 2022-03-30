package com.einhause.customer;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
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

        // checking if customer is fraud using service discovery
        FraudCheckHistoryResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckHistoryResponse.class,
                customer.getId()
        );

        log.info("Fraud check on customer {}", customer.getId());

        if (fraudCheckResponse.customerIsFraud()) {
            throw new IllegalStateException("Illegal customer");
        }

        // send notification
    }
}
