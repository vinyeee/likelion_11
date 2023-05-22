package com.jpaShop.book.repository;

import com.jpaShop.book.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    // 멤버저장
    public void save(Member member){
        em.persist(member);
    }

    // 멤버 id로 조회
    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    // 멤버 전체 조회
    public List<Member> findAll(){
        return em.createQuery("select m from Member m",Member.class)
                .getResultList();
    }

    // 이름으로 멤버조회
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)//파라미터 바인딩
                .getResultList();
    }

}
