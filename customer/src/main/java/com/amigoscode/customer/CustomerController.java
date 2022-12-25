package com.amigoscode.customer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
	
	private final CustomerService customerService;
	
	@PostMapping
	public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRequest) {
		log.info("new customer registration {}",customerRequest);
		customerService.registerCustomer(customerRequest);
	}
}
