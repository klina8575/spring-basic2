package com.example.springbasic2.entity;

import com.example.springbasic2.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity //엔티티 클래스로 정의
@Table(name="item") //테이블 이름 지정
@Getter
@Setter
@ToString
public class Item {
    @Id
    @Column(name="item_id") //테이블로 생성될때 컬럼이름을 지정해준다
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키를 자동증가 컬럼으로 생성해줌
    private Long id; //상품코드

    @Column(nullable = false, length = 50) //not null여부, 컬럼 크기지정
    private String itemNm; //상품명

    @Column(nullable = false)
    private int price; //가격

    @Column(nullable = false)
    private int stockNumber; //재고수량

    @Lob //clob과 같은 큰타입의 문자타입으로 컬럼을 만든다
    @Column(nullable = false, columnDefinition = "longtext")
    private String itemDetail; //상품상세설명

    @Enumerated(EnumType.STRING) //enum의 이름을 DB의 저장
    private ItemSellStatus itemSellStatus; //판매상태(SELL, SOLD_OUT)

    private LocalDateTime regTime; //등록시간

    private LocalDateTime updateTime; //수정시간
}
