package br.com.furrypetcare.domain.payment.dto;

import br.com.furrypetcare.domain.payment.PaymentStatus;
import jakarta.validation.constraints.NotNull;

public record UpdatePayment(
        @NotNull
        PaymentStatus paymentStatus
) {
}
