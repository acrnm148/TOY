package com.myapp.toyee.domain.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long memberNum;

    private String memberId;

    private String createdDatetime;

    private String useYsno;
}
