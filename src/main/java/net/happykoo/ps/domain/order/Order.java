package net.happykoo.ps.domain.order;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchase_order")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "order_id", columnDefinition = "BINARY(16)")
  private UUID orderId;

  private String name;

  private String phoneNumber;

  private String paymentId;

  private int totalPrice;

  @Column(name = "order_state")
  @Convert(converter = OrderStatusConverter.class)
  private OrderStatus status;

  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> items;

  @Builder
  public Order(String name, String phoneNumber) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.status = OrderStatus.ORDER_READY;
    this.items = new ArrayList<>();
  }

  public void complete() {
    if (hasEmptyItem()) {
      throw new IllegalArgumentException("Order should have at least one item.");
    }
    if (hasDuplicatedProductId()) {
      throw new IllegalArgumentException("Product id should be unique.");
    }
    for (OrderItem item : items) {
      item.complete();
    }
    this.totalPrice = calculateTotalAmount();
    this.status = OrderStatus.ORDER_COMPLETED;
  }

  public boolean isCompleted() {
    return status == OrderStatus.ORDER_COMPLETED;
  }

  public void addItems(List<OrderItem> items) {
    this.items.addAll(items);
  }

  public void paymentFulfill(String paymentKey) {
    this.paymentId = paymentKey;
    this.status = OrderStatus.PAYMENT_FULFILL;
  }

  private int calculateTotalAmount() {
    return items.stream()
        .map(OrderItem::calculateAmount)
        .reduce(0, Integer::sum);
  }

  private boolean hasDuplicatedProductId() {
    List<UUID> productIds = items.stream()
        .map(OrderItem::getProductId)
        .distinct()
        .toList();
    return items.size() > productIds.size();
  }

  private boolean hasEmptyItem() {
    return items == null || items.isEmpty();
  }
}
