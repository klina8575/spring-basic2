package com.example.springbasic2.repository;

import com.example.springbasic2.constant.ItemSellStatus;
import com.example.springbasic2.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    //select * from item where item_nm = ?
    List<Item> findByItemNm(String itemNm);

    //select * from item where item_nm = ? and item_sell_status = ?
    List<Item> findByItemNmAndItemSellStatus(String itemNm, ItemSellStatus itemSellStatus);

    //퀴즈1-2
    //select * from item where price between ? and ?
    List<Item> findByPriceBetween(int price1, int price2);

    //퀴즈1-3
    //select * from where reg_time > ?
    List<Item> findByRegTimeAfter(LocalDateTime regTime);

    //퀴즈1-4
    //select * from item where item_sell_status is not null;
    List<Item> findByItemSellStatusNotNull();

    //퀴즈 1-5
    //select * from item where item_detail like '%설명1';
    List<Item> findByItemDetailLike(String itemDetail);

    //퀴즈 1-6
    //select * from item where item_nm = ? or item_detail = ?
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    //퀴즈 1-7
    //select * from item where price < ? order by price desc
    List<Item> findByPriceLessThanOrderByPriceDesc(int price);

    //JPQL(non native 쿼리) -> 컬럼명, 테이블명은 entity 클래스 기준으로 작성한다.
    @Query("select i from Item i where i.itemDetail "
            + "like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);


    //JPQL(native 쿼리) -> h2 데이터베이스를 기준으로한 쿼리문 작성
    @Query(value="select * from item where item_detail "
            + "like %:itemDetail% order by price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);


    //퀴즈 2-1
    @Query("select i from Item i where i.price >= :price")
    List<Item> getPrice(@Param("price") int price);

    //퀴즈 2-2
    @Query("select i from Item i where i.itemNm = :itemNm and i.itemSellStatus = :sell")
    List<Item> getItemNmAndItemSellStatus(@Param("itemNm") String itemNm,
                                          @Param("sell") ItemSellStatus sell);

    //퀴즈 2-3
    @Query("select i from Item i where i.id = :id")
    List<Item> getIdSeven(@Param("id") Long id);
}
