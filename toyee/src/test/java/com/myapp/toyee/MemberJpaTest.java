package com.myapp.toyee;

import com.jayway.jsonpath.Criteria;
import com.myapp.toyee.domain.entity.Member;
import com.myapp.toyee.repository.MemberBaseCustomRepository;
import com.myapp.toyee.repository.MemberPagingRepository;
import com.myapp.toyee.repository.MemberQueryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//@DataJpaTest
@SpringBootTest
class MemberJpaTest {

    @Autowired
    private MemberBaseCustomRepository customRepository;

    @Autowired
    private MemberPagingRepository pagingRepository;

    @Autowired
    private MemberQueryRepository queryRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void custom_repository_test() {
        Member member = Member.builder()
                .memberId("13422")
                .useYsno("Y")
                .build();
        customRepository.save(member);
        Assertions.assertThat(Collections.singletonList(customRepository.findAll()).size()).isEqualTo(1);
    }

    @Test
    public void name_rule_test() {
        customRepository.save(Member.builder()
                .memberId("13422")
                .useYsno("Y")
                .build());
        Member member = customRepository.findDistinctMemberByMemberNumOrderByMemberNum(1L);
        Assertions.assertThat("13422".equals(member.getMemberId()));
    }

    @Test
    public void paging_param_test() {
        customRepository.save(Member.builder()
                .memberId("13422")
                .useYsno("Y")
                .build());
        customRepository.save(Member.builder()
                .memberId("13423")
                .useYsno("Y")
                .build());
        customRepository.save(Member.builder()
                .memberId("13424")
                .useYsno("Y")
                .build());

        Pageable pageable = PageRequest.of(0, 2);
        Assertions.assertThat(pageable.getPageNumber()).isEqualTo(0);
        Assertions.assertThat(pagingRepository.findAll(pageable)).hasSize(2);

        // 다음 페이지
        Pageable nextPageable = pageable.next();
        Assertions.assertThat(pagingRepository.findAll(nextPageable)).hasSize(1);

        // 정렬
        Pageable sortPageable = PageRequest.of(0, 2, Sort.by("memberId").descending());
        Assertions.assertThat(pagingRepository.findAll(sortPageable)).hasSize(2);
    }

    @Test
    public void named_query_test() {
        customRepository.save(Member.builder()
                .memberId("13422")
                .useYsno("Y")
                .build());
        customRepository.save(Member.builder()
                .memberId("13423")
                .useYsno("Y")
                .build());
        Assertions.assertThat(queryRepository.findAllByMemberNumAndMemberId(1L, "13422")).hasSize(1);
        Assertions.assertThat(queryRepository.findAllOrderByMemberNum(2L)).hasSize(2);
        Assertions.assertThat(queryRepository.findAllByMemberId("13423")).size().isEqualTo(1);
    }

    @Test
    public void query_test() {
        customRepository.save(Member.builder()
                .memberId("13422")
                .useYsno("Y")
                .createdDatetime("2024-05-26")
                .build());
        customRepository.save(Member.builder()
                .memberId("13423")
                .useYsno("Y")
                .createdDatetime("2024-05-26")
                .build());
        Assertions.assertThat(queryRepository.findAllByUseYsno("N")).size().isEqualTo(0);
        Assertions.assertThat(queryRepository.findAllByCreatedDatetimeGreaterThan("2024-05-26")).size().isEqualTo(2);
    }

    @Test
    public void query_modify_test() {
        customRepository.save(Member.builder()
                .memberId("13422")
                .useYsno("Y")
                .createdDatetime("2024-05-26")
                .build());
        queryRepository.updateMemberIdByMemberNum("강수나", 1L);
        Assertions.assertThat(customRepository.findAllByMemberNum(1L)).size().isEqualTo(1);
    }

    //1968ms
    @Test
    public void save_test() {
        for (int i=0; i<300; i++) {
            queryRepository.save(Member.builder()
                    .memberId("1342"+i)
                    .useYsno("Y")
                    .createdDatetime("2024-05-26")
                    .build());
        }
        Assertions.assertThat(customRepository.findAll()).size().isEqualTo(300);
    }

    // 944ms
    @Test
    public void save_all_test() {
        List<Member> members = new ArrayList<>();
        for (int i=0; i<300; i++) {
            members.add(Member.builder()
                            .memberId("1342"+i)
                            .useYsno("Y")
                            .createdDatetime("2024-05-26")
                            .build());
        }
        queryRepository.saveAll(members);
        Assertions.assertThat(customRepository.findAll()).size().isEqualTo(300);
    }

    @Test
    public void bulk_update() {
        List<Member> members = new ArrayList<>();
        for (int i=0; i<300; i++) {
            members.add(Member.builder()
                    .memberId("1342"+i)
                    .useYsno("Y")
                    .createdDatetime("2024-05-26")
                    .build());
        }
        queryRepository.saveAll(members);
        queryRepository.updateAllMemberIds();

        List<Member> result = (List<Member>) customRepository.findAllByMemberNum(1L);
        Member member = result.get(0);
        System.out.println("memberId: " + member.getClass());
    }


    @Test
    public void criteria_api_test() {
        List<Member> members = new ArrayList<>();
        for (int i=0; i<3; i++) {
            members.add(Member.builder()
                    .memberId("1342"+i)
                    .useYsno("Y")
                    .createdDatetime("2024-05-26")
                    .build());
        }
        queryRepository.saveAll(members);

        // select * from member where member_id=?
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Member> memberCriteriaQuery = builder.createQuery(Member.class);
        Root<Member> memberRoot = memberCriteriaQuery.from(Member.class);
        Predicate memberPredicate = builder.equal(memberRoot.get("memberId"), "13422");
        memberCriteriaQuery.where(memberPredicate);
        TypedQuery<Member> query = entityManager.createQuery(memberCriteriaQuery);

        Assertions.assertThat(query.getResultList().size()).isEqualTo(1);
    }
}
