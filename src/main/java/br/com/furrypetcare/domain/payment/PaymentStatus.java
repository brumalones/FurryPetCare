package br.com.furrypetcare.domain.payment;

public enum PaymentStatus {
    CANCELED("Payment Cancelled"),
    APPROVED("Payment Approved"),
    PENDING("Payment Pending");

    private final String displayName;

    PaymentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
