package net.happykoo.ps.representation.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.happykoo.ps.representation.in.web.request.order.PurchaseOrder;
import net.happykoo.ps.representation.in.web.response.order.NewPurchaseOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  @PostMapping("/new")
  public NewPurchaseOrder newOrder(@RequestBody @Valid PurchaseOrder newOrder) {
    return null;
  }
}
