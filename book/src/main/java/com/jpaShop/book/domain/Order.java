package com.jpaShop.book.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "member_id")//fk 값의 컬럼명
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // 연관관계의 주인 맵핑
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // == 연관관계 편의 매서드 ==//
    // Member <--> Order : 주문하는 회원을 저장할 때

    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this); //특정 멤버가 주문한 order리스트를 얻어와서 이 객체 (order)를 추가

    }
    // OrderItem <--> Order : 주문상품 엔터티를 추가할 때
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // Delivery <--> Order : 배송을 생성할 때
    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    // == 생성 메서드 == //
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        return order;

    }

    // == 비즈니스 로직 == //
    // 주문 취소 : Delivery Status 에 따라 취소 가능 -> 취소상태로 전환 & 재고 되돌리기
    // 취소 불가능 -> throw Exception
    public void cancel() {
        if(delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송이 완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCLE);
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }
}
