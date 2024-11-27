package org.example.springshop.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User와의 다대일 관계
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // OrderItem과의 일대다 관계
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    // Delivery와의 일대일 관계
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Delivery delivery;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Builder
    public Order(Long id, User user, List<OrderItem> orderItems,
                 Delivery delivery, OrderStatus status) {
        this.id = id;
        this.user = user;
        this.orderItems = orderItems != null ? orderItems : new ArrayList<>();
        this.delivery = delivery;
        this.status = status;
        this.orderDate = LocalDateTime.now();
    }

    // 총 주문 수량 반환
    public int getTotalQuantity() {
        return orderItems.stream()
                .mapToInt(OrderItem::getCount)
                .sum();
    }

    // 대표 상품 이름 반환 (첫 번째 상품 기준)
    public String getFirstItemName() {
        return orderItems.isEmpty() ? null : orderItems.get(0).getItem().getName();
    }

    public Item getItem() {
        return null;
    }





}
