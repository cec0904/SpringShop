package org.example.springshop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Item과의 다대일 관계
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    // Order와의 다대일 관계
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private int orderPrice; // 주문 당시 상품 가격

    private int count; // 주문 수량
}
