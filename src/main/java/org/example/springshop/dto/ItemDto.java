package org.example.springshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    private Long id;
    private String name;
    private int price;
    private int stock;
    private String categoryName; // 카테고리 이름 (Category와 연관)

    // Item 엔티티를 DTO로 변환
    public static ItemDto fromEntity(org.example.springshop.domain.Item item) {
        // Category의 이름 가져오기 (Category는 ManyToMany 관계)
        String categoryName = item.getCategories().isEmpty() ? null : item.getCategories().get(0).getName();

        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .stock(item.getStock())
                .categoryName(categoryName) // 첫 번째 카테고리 이름만 사용
                .build();
    }

    // DTO를 Item 엔티티로 변환
    public org.example.springshop.domain.Item toEntity() {
        return org.example.springshop.domain.Item.builder()
                .name(this.name)
                .price(this.price)
                .stock(this.stock)
                .build();
    }
}
