package com.myapp.toyee.Service;

import com.myapp.toyee.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    /*private final MemberRepository memberRepository;

    public Member getMemberInfo(Long memberNum) {
        Optional<Member> optionalMember = memberRepository.findById(memberNum);
        *//*MemberDto memberDto = null;
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            //memberDto = member.
        }*//*
        Member member = optionalMember.orElse(null);
        log.info("조회된 member:", member);
        return member;
    }*/
}
