package com.myapp.toyee.repository;

import com.myapp.toyee.domain.entity.Member;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface MemberQueryRepository extends CrudRepository<Member, Long> {
    // @NamedQuery
    Iterable<Member> findAllByMemberNumAndMemberId(Long memberNum, String memberId);
    Iterable<Member> findAllOrderByMemberNum(Long memberNum);

    // @NamedNativeQuery
    Iterable<Member> findAllByMemberId(@Param("memberId") String memberId);

    // @Query
    @Query("select m from Member m where m.useYsno = ?1")
    Iterable<Member> findAllByUseYsno(@Param("useYsno") String useYsno);

    @Query(value = "select * from Member m where m.created_datetime >= :createdDatetime", nativeQuery = true)
    Iterable<Member> findAllByCreatedDatetimeGreaterThan(@Param("createdDatetime") String createdDatetime);

    @Modifying
    @Transactional
    @Query(value = "update Member set member_id = :memberId where member_num = :memberNum", nativeQuery = true)
    int updateMemberIdByMemberNum(@Param("memberId") String memberId, @Param("memberNum") Long memberNum);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update Member set member_id = concat(member_id, '~')", nativeQuery = true)
    int updateAllMemberIds();
}
