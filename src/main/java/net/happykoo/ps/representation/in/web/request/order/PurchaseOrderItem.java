package net.happykoo.ps.representation.in.web.request.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record PurchaseOrderItem(
    @Min(1)
    int itemIdx,

    UUID productId,

    @NotBlank
    String productName,

    @Min(0)
    int price,

    @Min(1)
    int quantity,

    @Min(0)
    int amounts // price * quantity
) {

}
