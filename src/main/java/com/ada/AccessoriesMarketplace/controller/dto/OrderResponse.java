package com.ada.AccessoriesMarketplace.controller.dto;

import com.ada.AccessoriesMarketplace.model.Product;
import com.ada.AccessoriesMarketplace.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class OrderResponse {
    private Integer id;
    private Double totalPrice;
    private User user;
    private List<Product> products;
}
