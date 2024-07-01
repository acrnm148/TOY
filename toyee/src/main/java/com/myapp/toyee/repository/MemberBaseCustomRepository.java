package com.myapp.toyee.repository;

import com.myapp.toyee.domain.entity.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MemberBaseCustomRepository extends MemberBaseRepository<Member, Long> {
}


