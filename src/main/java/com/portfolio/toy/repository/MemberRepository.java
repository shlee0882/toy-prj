package com.portfolio.toy.repository;

import java.util.List;

import com.portfolio.toy.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{

	public List<Member> findByUserId(String userId);
	
}
