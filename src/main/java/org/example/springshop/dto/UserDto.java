package org.example.springshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springshop.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String nickname;
    private CharSequence password;

    // Member 엔티티를 DTO로 변환
    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }

    // DTO를 Member 엔티티로 변환
    public org.example.springshop.domain.User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(this.email)
                .nickname(this.nickname)
                .password(passwordEncoder.encode(this.password)) // 비밀번호 인코딩 처리
                .build();
    }
}
