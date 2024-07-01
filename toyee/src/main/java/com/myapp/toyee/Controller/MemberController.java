package com.myapp.toyee.Controller;

import com.myapp.toyee.Service.MemberService;
import com.myapp.toyee.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<Member> getMemberInfo(@RequestParam(value = "memberNum") Long memberNum) {
        //return new ResponseEntity<> (memberService.getMemberInfo(memberNum), HttpStatus.OK);
        return null;
    }

}
