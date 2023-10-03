package com.ada.AccessoriesMarketplace.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductResponse {
    private Integer id;
    private String name;
    private Double price;
    private TypeProductResponse type;
}
