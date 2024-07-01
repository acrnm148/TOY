package com.myapp.toyee;

import com.myapp.toyee.domain.entity.Member;
import com.myapp.toyee.repository.MemberBaseCustomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
public class MemberTest {

    /*@Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;*/

    /*@Test
    void 회원_등록() {
        IntStream.range(0, 20).forEach(i -> {
            Member member = Member.builder()
                    .memberId("tester"+i)
                    .useYsno("Y")
                    .build();
            memberRepository.save(member);
        });

    }*/

   /* @Test
    void 회원_조회() {
        System.out.println(memberService.getMemberInfo(1L));
    }*/

    @Autowired
    private MemberBaseCustomRepository customRepository;

    @Test
    public void custom_repository_test() {
        Member member = Member.builder()
                .memberId("13422")
                .useYsno("Y")
                .build();
        customRepository.save(member);
        Assertions.assertThat(Collections.singletonList(customRepository.findAll()).size()).isEqualTo(1);
    }
}
