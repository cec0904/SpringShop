package org.example.springshop.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30; // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; // 7일

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String username = authentication.getName();

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key)
                .compact();

        // Access Token: JSON 응답으로 전달
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write("{\"accessToken\": \"" + accessToken + "\"}");
        } catch (Exception e) {
            throw new RuntimeException("Access Token 응답 중 오류 발생", e);
        }

        // Refresh Token: 쿠키에 저장
        Cookie refreshCookie = new Cookie("refresh_token", refreshToken);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge((int) (REFRESH_TOKEN_EXPIRE_TIME / 1000));
        response.addCookie(refreshCookie);

        // OAuth2 상태 저장
        Cookie oauthCookie = new Cookie("oauth2_auth_request", "true");
        oauthCookie.setHttpOnly(false);
        oauthCookie.setPath("/");
        response.addCookie(oauthCookie);
    }
}
