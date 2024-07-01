package com.myapp.toyee.repository;

import com.myapp.toyee.domain.entity.Member;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.stream.Stream;

@NoRepositoryBean
public interface MemberBaseRepository<T, ID> extends Repository<T, ID> {
    <S extends T> S save(S entity);
    Iterable<T> findAll();
    Member findDistinctMemberByMemberNumOrderByMemberNum(long l);

    Iterable<Member> findAllByMemberNum(Long l);
    Iterable<Member> findAllByMemberNumOrderByMemberNum(Long l);
    boolean existsByMemberId(String id);
    long countByMemberNum(long l);
    Iterable<Member> findByMemberNumOrMemberId(Long l, String id);
    Iterable<Member> findByMemberIdStartsWith(String id);
    Stream<Member> streamAllByMemberId(String id);
}


