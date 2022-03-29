package com.einhause.fraud;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fraud-check")
@AllArgsConstructor
public class FraudCheckHistoryController {

    private final FraudCheckHistoryService fraudCheckHistoryService;

    @GetMapping(path = "{customerId}")
    public FraudCheckHistoryResponse customerIsFraud(@PathVariable("customerId") Long customerId) {
        boolean isFradulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerId);
        return new FraudCheckHistoryResponse(isFradulentCustomer);
    }

}
