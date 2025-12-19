package net.happykoo.ps.representation.in.web.request.payment;

public record PaymentCancel(
    String cancelReason,
    int cancelAmount
) {

}
