package org.example.springshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springshop.domain.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String nickname;

    // Member 엔티티를 DTO로 변환
    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }

    // DTO를 Member 엔티티로 변환
    public org.example.springshop.domain.User toEntity() {
        return User.builder()
                .email(this.email)
                .nickname(this.nickname)
                .build();
    }
}
