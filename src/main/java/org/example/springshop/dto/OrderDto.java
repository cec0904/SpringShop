package org.example.springshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springshop.domain.User;


import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private String memberEmail; // 주문자 이메일
    private String itemName; // 주문 상품 이름
    private int quantity;       // 주문 수량
    private Date orderDate; // 주문일

    // Order 엔티티를 DTO로 변환
    public static OrderDto fromEntity(org.example.springshop.domain.Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .memberEmail(order.getUser().getEmail()) // Member의 이메일
                .itemName(order.getItem().getName()) // Item 이름
                .quantity(order.getTotalQuantity())
                .orderDate(order.getOrderDate())
                .build();
    }

    // DTO를 Order 엔티티로 변환
    public org.example.springshop.domain.Order toEntity(
            User user,
            org.example.springshop.domain.Item item
    ) {
        return org.example.springshop.domain.Order.builder()
                .user(user)
                .orderItems(item)
                .count(this.quantity)
                .orderDate(this.orderDate)
                .build();
    }
}
