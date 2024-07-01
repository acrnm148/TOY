package com.myapp.toyee.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Builder
@Entity
@Table(name = "member")
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Member.findAllByMemberNumAndMemberId",
                query = "select m from Member m where m.memberNum=?1 and m.memberId=?2"),
        @NamedQuery(name = "Member.findAllOrderByMemberNum",
                query = "select m from Member m where memberId like '1342%' order by memberNum")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "Member.findAllByMemberId"
                , query = "select * from Member m where m.member_id = ?1"
                ,resultClass = Member.class)
})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNum;

    private String memberId;

    @DateTimeFormat
    private String createdDatetime;

    private String useYsno;

    @Builder
    public Member(Long memberNum, String memberId, String createdDatetime, String useYsno) {
        this.memberNum = memberNum;
        this.memberId = memberId;
        this.createdDatetime = createdDatetime;
        this.useYsno = useYsno;
    }
}
