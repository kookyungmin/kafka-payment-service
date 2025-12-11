package net.happykoo.ps.representation.request.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record PurchaseOrderItem(
    @Min(1)
    int itemIdx,

    UUID productId,

    @NotBlank
    String productName,

    int price,

    @Min(1)
    int quantity,

    int amounts // price * quantity
) {

}
