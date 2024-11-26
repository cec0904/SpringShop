package org.example.springshop.repository;

import org.example.springshop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
}
