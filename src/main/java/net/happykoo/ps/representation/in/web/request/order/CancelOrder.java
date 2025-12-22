package net.happykoo.ps.representation.in.web.request.order;

import java.util.UUID;

public record CancelOrder(
    UUID orderId,
    int[] itemIdxs,
    String cancelReason,
    int cancellationAmount
) {

  public boolean hasItemIdx() {
    return itemIdxs != null && itemIdxs.length > 0;
  }

}
