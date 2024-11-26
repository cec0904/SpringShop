package org.example.springshop.config;

import org.example.springshop.domain.User;
import org.example.springshop.repository.UserRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String nickname = oAuth2User.getAttribute("name");

        Optional<User> existingMember = userRepository.findByEmail(email);

        if (existingMember.isEmpty()) {
            // 회원이 없으면 자동으로 등록
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setNickname(nickname);
            userRepository.save(newUser);
        }

        return oAuth2User;
    }
}
