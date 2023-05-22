package com.jpaShop.book.service;

import com.jpaShop.book.domain.Member;
import com.jpaShop.book.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional //클래스 내의 매서드 각각을 트랜젝션 단위로 동작하게끔 함
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    //회원가입
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        List<Member> members = memberRepository.findByName(member.getName());
        if(!members.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }

    }
    //전체회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //하나의 회원 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
