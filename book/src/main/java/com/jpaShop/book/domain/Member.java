package com.jpaShop.book.domain;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") //연관관계의 주인맵핑, 상대객체에서 이 객체를 참조할 때 사용한 필드명
    private List<Order> orders = new ArrayList<>();
}
