package org.example.springshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private int price;
    private int stock;
    private String categoryName; // 카테고리 이름 (Category와 연관)

    // Product 엔티티를 DTO로 변환
    public static ProductDto fromEntity(org.example.springshop.domain.Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .categoryName(product.getCategory().getName()) // 연관된 Category의 이름
                .build();
    }

    // DTO를 Product 엔티티로 변환
    public org.example.springshop.domain.Product toEntity(org.example.springshop.domain.Category category) {
        return org.example.springshop.domain.Product.builder()
                .name(this.name)
                .price(this.price)
                .stock(this.stock)
                .category(category) // 연관된 Category 설정
                .build();
    }
}
