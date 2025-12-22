package net.happykoo.ps.domain.order;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "item_idx")
  private int itemIdx;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  private UUID productId;

  private String productName;

  @Column(name = "product_price")
  private int price;

  @Column(name = "product_size")
  private String size;

  private int amount;

  private int quantity;

  @Column(name = "order_state")
  @Convert(converter = OrderStatusConverter.class)
  private OrderStatus state;

  public int calculateAmount() {
    int amount = price * quantity;
    this.amount = amount;
    return amount;
  }

  public void complete() {
    this.state = OrderStatus.ORDER_COMPLETED;
  }

  public void cancel() {
    this.state = OrderStatus.ORDER_CANCELLED;
  }
}
