package org.example.springshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        // 인증 없이 접근 가능한 경로
                        .requestMatchers("/user").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/js/**", "/img/**", "/css/**", "/vendor/**").permitAll()
                        .requestMatchers("/api/token").permitAll()

                        // 인증이 필요한 경로
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
                )
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (필요 시 활성화 가능)
                .formLogin(form -> form
                        .loginPage("/login") // 커스텀 로그인 페이지 경로
                        .defaultSuccessUrl("/") // 로그인 성공 시 리다이렉트 경로
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 URL
                        .invalidateHttpSession(true) // 세션 무효화
                        .deleteCookies("refresh_token", "oauth2_auth_request") // 쿠키 삭제
                        .permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // OAuth2 로그인 페이지 경로
                        .defaultSuccessUrl("/") // OAuth2 로그인 성공 시 리다이렉트 경로
                );

        return http.build();
    }
}
