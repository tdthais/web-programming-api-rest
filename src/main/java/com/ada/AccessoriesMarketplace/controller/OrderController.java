package com.ada.AccessoriesMarketplace.controller;

import com.ada.AccessoriesMarketplace.controller.dto.OrderRequest;
import com.ada.AccessoriesMarketplace.controller.dto.OrderResponse;
import com.ada.AccessoriesMarketplace.model.Order;
import com.ada.AccessoriesMarketplace.service.OrderService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> saveOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = orderService.saveOrder(orderRequest);
        return ResponseEntity.created(URI.create("/order/"+orderResponse.getId())).body(orderResponse);
    }


    @GetMapping
    public ResponseEntity<Page<OrderResponse>> getOrder(
            @QuerydslPredicate(root = Order.class) Predicate predicate,
            Pageable pageable

    ){
       return ResponseEntity.ok(orderService.getAllOrders(predicate, pageable));
    }



}
