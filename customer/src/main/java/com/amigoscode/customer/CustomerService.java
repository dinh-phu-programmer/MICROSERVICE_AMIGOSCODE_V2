package com.amigoscode.customer;

import com.amigoscode.amqp.RabbitMQMessageProducer;
import com.amigoscode.clients.fraud.FraudCheckResponse;
import com.amigoscode.clients.fraud.FraudClient;
import com.amigoscode.clients.notification.NotificationClient;
import com.amigoscode.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepostiry customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    @Transactional
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).build();

        // todo: check if email valid
        // todo: check if email not taken
        // todo: store customer id db
        customerRepository.saveAndFlush(customer);
        // todo: check if fraudster

//		FraudCheckResponse fraudCheckResponse= restTemplate.getForObject(
//				"http://fraud/api/v1/fraud-check/{customerId}",
//				FraudCheckResponse.class,
//				customer.getId()
//		);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.getIsFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        // todo: send notification
//		notificationClient.sendNotification(
//				NotificationRequest.builder()
//						.toCustomerId(customer.getId())
//						.toCustomerEmail(customer.getEmail())
//						.message(String.format("Hi %s, welcome to CodingHaki...",customer.getFirstName()))
//						.build()
//		);
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .toCustomerId(customer.getId())
                .toCustomerEmail(customer.getEmail())
                .message(String.format("Hi %s, welcome to CodingHaki...", customer.getFirstName()))
                .build();

        rabbitMQMessageProducer.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");

    }

}
