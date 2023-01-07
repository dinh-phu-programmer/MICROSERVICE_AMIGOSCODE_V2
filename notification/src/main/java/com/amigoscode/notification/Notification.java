package com.amigoscode.notification;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name="notification")
public class Notification {
    @Id
    @SequenceGenerator(name="notification_id_sequence",sequenceName = "notification_id_sequence")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "notification_id_sequence")
    private Integer notificationId;
    private Integer toCutomerId;
    private String toCustomerEmail;
    private String sender;
    private String message;
    private LocalDateTime sentAt;
}
