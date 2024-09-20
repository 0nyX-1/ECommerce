package com.sohan.kafka;

import com.sohan.email.EmailService;
import com.sohan.kafka.Order.OrderConfirmation;
import com.sohan.kafka.Payment.PaymentConfirmation;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumerPaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming the message from payment topic",paymentConfirmation);

        String fullName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                fullName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumerPaymentSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming the message from order topic",orderConfirmation);

        String fullName = orderConfirmation.customerFirstName() + " " + orderConfirmation.customerLastName();

        emailService.sendOrderConfirmation(
                orderConfirmation.customerEmail(),
                fullName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}
