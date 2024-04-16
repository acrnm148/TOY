package com.myapp.toyee.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Builder
@Entity
@Table
@NoArgsConstructor
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
