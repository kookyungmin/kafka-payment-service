package net.happykoo.ps.representation.request.order;

import jakarta.validation.constraints.NotBlank;

public record Orderer(
    @NotBlank
    String name,
    @NotBlank
    String phoneNumber
) {

}
