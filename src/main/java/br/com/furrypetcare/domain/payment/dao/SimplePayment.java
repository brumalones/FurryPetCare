package br.com.furrypetcare.domain.payment.dao;

import br.com.furrypetcare.domain.cart.dao.SimpleCart;
import br.com.furrypetcare.domain.payment.PaymentEntity;
import br.com.furrypetcare.domain.payment.PaymentStatus;

import java.util.Date;
import java.util.UUID;

public record SimplePayment(
        UUID id,
        SimpleCart cart,
        double totalAmount,
        PaymentStatus paymentStatus,
        Date paymentDate
) {


    public SimplePayment(PaymentEntity paymentEntity) {
        this(
                paymentEntity.getId(),
                new SimpleCart(paymentEntity.getCart()),
                paymentEntity.getTotalAmount(),
                paymentEntity.getPaymentStatus(),
                paymentEntity.getPaymentDate()
        );
    }
}
