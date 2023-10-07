package com.ada.Bookshop.service;

import com.ada.Bookshop.controller.dto.OrderRequest;
import com.ada.Bookshop.controller.dto.OrderResponse;
import com.ada.Bookshop.model.Order;
import com.ada.Bookshop.model.Product;
//import com.ada.AccessoriesMarketplace.model.QOrder;
import com.ada.Bookshop.model.User;
import com.ada.Bookshop.repository.OrderRepository;
import com.ada.Bookshop.repository.ProductRepository;
import com.ada.Bookshop.repository.UserRepository;
import com.ada.Bookshop.utils.OrderConvert;
import com.querydsl.core.types.Predicate;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    EntityManager entityManager;

    public OrderResponse saveOrder(OrderRequest orderRequest){
        User user = userRepository.findById(orderRequest.getUserId()).get();

        List<Product> products = new ArrayList<>();

        List<Integer> productsId = orderRequest.getProductsIds();

        for(Integer id: productsId){
            Product product = productRepository.findById(id).get();
            products.add(product);
        }

        Order order = OrderConvert.toEntity(orderRequest, user, products);


        return OrderConvert.toResponse(orderRepository.save(order));
    }

    public Page<OrderResponse> getAllOrders(
            Predicate predicate,
            Pageable pageable
    ){
        Page<Order> orders = orderRepository.findAll(predicate, pageable);

        return OrderConvert.toResponsePage(orders);
    }

    public List<OrderResponse> getAllByUser(Integer userId){
        return OrderConvert.toResponseList(orderRepository.findAllByUser(userId));
    }

    public List<OrderResponse> getAllByProduct(Integer productId){
        return OrderConvert.toResponseList(orderRepository.findAllByProduct(productId));
    }
//    public List<OrderResponse> getAllByPrice(Double minValue, Double maxValue){
//        JPAQuery<Order> query = new JPAQuery<>(entityManager);
//        QOrder qOrder = QOrder.order;
//
//        List<Order> orders = query.from(qOrder).where(qOrder.totalPrice.between(minValue, maxValue)).fetch();
//        return OrderConvert.toResponseList(orders);
//    }
}
