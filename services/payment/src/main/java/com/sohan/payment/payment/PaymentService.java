package com.sohan.payment.payment;

import com.sohan.payment.notification.NotificationProducer;
import com.sohan.payment.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepo repo;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest paymentRequest) {
        Payment payment = repo.save(mapper.toPayment(paymentRequest));
        notificationProducer.sendNotification(
            new PaymentNotificationRequest(
                    paymentRequest.orderReference(),
                    paymentRequest.amount(),
                    paymentRequest.paymentMethod(),
                    paymentRequest.customerFirstName(),
                    paymentRequest.customerLastName(),
                    paymentRequest.customerEmail()
            )
        );
        return payment.getId();
    }
}
