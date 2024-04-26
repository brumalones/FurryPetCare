package br.com.furrypetcare.domain.payment;

import br.com.furrypetcare.domain.cart.CartRepository;
import br.com.furrypetcare.domain.payment.dao.DetailPayment;
import br.com.furrypetcare.domain.payment.dto.ResgiterPayment;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ComponentPayment {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    CartRepository cartRepository;

    public PaymentEntity registerPayment(ResgiterPayment resgiterPayment) {
        var optionalCartEntity = cartRepository.findById(resgiterPayment.cartId());
        return paymentRepository.save(
                new PaymentEntity(
                        optionalCartEntity.orElseThrow(() -> new EntityNotFoundException("Cart not found"))
                ));
    }

    public DetailPayment previewPayment(UUID cartId) {
        var optionalCartEntity = cartRepository.findById(cartId);
        return new DetailPayment(
                optionalCartEntity.orElseThrow(() -> new EntityNotFoundException("Cart not found"))
        );

    }

}
