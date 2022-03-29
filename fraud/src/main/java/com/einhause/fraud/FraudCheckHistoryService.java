package com.einhause.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckHistoryService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(Long customerId) {
        // for simplicity, assume every customer is legit
        // use 3rd party checker IRL
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .customerIsFraud(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }

}
