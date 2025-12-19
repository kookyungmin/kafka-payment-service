package net.happykoo.ps.representation.in.web.request.payment;

public record PaymentApproved(
    String paymentType,
    String paymentKey,
    String orderId,
    String amount
) {

}
