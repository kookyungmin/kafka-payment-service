package net.happykoo.ps.representation.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.happykoo.ps.application.port.in.CreateNewOrderUseCase;
import net.happykoo.ps.representation.in.web.request.order.PurchaseOrder;
import net.happykoo.ps.representation.in.web.response.order.NewPurchaseOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final CreateNewOrderUseCase createNewOrderUseCase;

  @PostMapping("/new")
  public NewPurchaseOrder newOrder(@RequestBody @Valid PurchaseOrder newOrder) {
    return NewPurchaseOrder.from(createNewOrderUseCase.createOrder(newOrder));
  }
}
