package br.com.furrypetcare.domain.payment.dao;


import br.com.furrypetcare.domain.cart.CartEntity;
import br.com.furrypetcare.domain.cart.dao.DetailCart;
import br.com.furrypetcare.domain.services.ServiceEntity;
import br.com.furrypetcare.domain.payment.PaymentEntity;
import br.com.furrypetcare.domain.payment.PaymentStatus;

import java.util.Date;
import java.util.UUID;

public record DetailPayment(
        UUID id,
        DetailCart cart,
        Double totalAmount,
        PaymentStatus paymentStatus,
        Date paymentDate
) {

    public DetailPayment(CartEntity cartEntity) {
        this(
                null,
                new DetailCart(cartEntity),
                cartEntity.getServices().stream().mapToDouble(ServiceEntity::getPrice).sum(),
                PaymentStatus.PENDING,
                null
        );
    }

    public DetailPayment(PaymentEntity payment) {
        this(
                payment.getId(),
                new DetailCart(payment.getCart()),
                payment.getTotalAmount(),
                payment.getPaymentStatus(),
                payment.getPaymentDate()
        );
    }
}
