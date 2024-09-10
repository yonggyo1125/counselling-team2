package com.thxforservice.member.repositories;

import com.thxforservice.member.entities.User;
import com.thxforservice.member.entities.QMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {
    Optional<User> findByEmail(String email);

    default boolean exists(String email) {
        QMember member = QMember.member;

        return exists(member.email.eq(email));
    }
}