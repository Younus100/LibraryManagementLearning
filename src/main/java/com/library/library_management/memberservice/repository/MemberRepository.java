package com.library.library_management.memberservice.repository;

import com.library.library_management.memberservice.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
