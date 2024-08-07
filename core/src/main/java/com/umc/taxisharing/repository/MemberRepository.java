package com.umc.taxisharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umc.taxisharing.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}