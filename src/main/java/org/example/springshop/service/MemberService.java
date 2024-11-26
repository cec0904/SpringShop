package org.example.springshop.service;
import org.example.springshop.domain.Member;
import org.example.springshop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member registerMember(Member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new IllegalStateException("이미 사용 중인 이메일입니다.");
        }
        return memberRepository.save(member);
    }
}
