package com.amigoscode.customer;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {

	private final CustomerRepostiry customerRepository;

	public void registerCustomer(CustomerRegistrationRequest request) {
		Customer customer = Customer.builder().firstName(request.getFirstName()).lastName(request.getLastName())
				.email(request.getEmail()).build();

		// todo: check if email valid
		// todo: check if email not taken
		// todo: store customer id db
		customerRepository.save(customer);
	}

}
