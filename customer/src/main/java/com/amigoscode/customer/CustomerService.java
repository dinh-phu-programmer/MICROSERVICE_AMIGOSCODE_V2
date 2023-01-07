package com.amigoscode.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepostiry customerRepository;
	private final RestTemplate restTemplate;
	public void registerCustomer(CustomerRegistrationRequest request) {
		Customer customer = Customer.builder().firstName(request.getFirstName()).lastName(request.getLastName())
				.email(request.getEmail()).build();

		// todo: check if email valid
		// todo: check if email not taken
		// todo: store customer id db
		customerRepository.saveAndFlush(customer);
        // todo: check if fraudster

		FraudCheckResponse fraudCheckResponse= restTemplate.getForObject(
				"http://localhost:8081/api/v1/fraud-check/{customerId}",
				FraudCheckResponse.class,
				customer.getId()
		);

		if(fraudCheckResponse.getIsFraudster()){
			throw new IllegalStateException("fraudster");
		}

		// todo: send notification
	}

}
