package com.einhause.fraud;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/v1/fraud-check")
@AllArgsConstructor
public class FraudCheckHistoryController {

    private final FraudCheckHistoryService fraudCheckHistoryService;

    @GetMapping(path = "{customerId}")
    public FraudCheckHistoryResponse customerIsFraud(@PathVariable("customerId") Long customerId) {
        boolean isFradulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerId);

        log.info("Fraud check on customer {}", customerId);

        return new FraudCheckHistoryResponse(isFradulentCustomer);
    }

}
