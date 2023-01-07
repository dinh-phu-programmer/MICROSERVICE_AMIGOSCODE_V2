package com.amigoscode.clients.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationRequest {
    private Integer toCustomerId;
    private String toCustomerEmail;
    private String message;
}
