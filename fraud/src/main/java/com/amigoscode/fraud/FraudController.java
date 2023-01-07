package com.amigoscode.fraud;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@RequiredArgsConstructor
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping("{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId){
        boolean isFraudCustomer =fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraudCustomer);
    }
}
