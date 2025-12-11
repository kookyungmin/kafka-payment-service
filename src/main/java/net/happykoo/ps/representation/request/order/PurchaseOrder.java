package net.happykoo.ps.representation.request.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record PurchaseOrder(
    @NotNull(message = "The orderer is required.")
    @Valid
    Orderer orderer,

    @Size(min = 1)
    @Valid
    List<PurchaseOrderItem> newlyOrderedItem
) {

}
