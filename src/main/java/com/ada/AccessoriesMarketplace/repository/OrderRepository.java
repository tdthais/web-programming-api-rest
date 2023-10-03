package com.ada.AccessoriesMarketplace.repository;

import com.ada.AccessoriesMarketplace.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, QuerydslPredicateExecutor<Order> {

    @Query(value = "SELECT * FROM ORDERS WHERE USER_ID = :userId", nativeQuery = true)
    List<Order> findAllByUser(Integer userId);

    @Query(value = "SELECT order FROM Order order JOIN order.products product WHERE product.id = :productId")
    List<Order> findAllByProduct(Integer productId);

}
