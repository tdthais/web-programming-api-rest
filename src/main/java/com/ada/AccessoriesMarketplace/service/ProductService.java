package com.ada.AccessoriesMarketplace.service;


import com.ada.AccessoriesMarketplace.controller.dto.ProductRequest;
import com.ada.AccessoriesMarketplace.controller.dto.ProductResponse;
import com.ada.AccessoriesMarketplace.model.Product;
import com.ada.AccessoriesMarketplace.model.TypeProduct;
import com.ada.AccessoriesMarketplace.repository.ProductRepository;
import com.ada.AccessoriesMarketplace.repository.TypeProductRepository;
import com.ada.AccessoriesMarketplace.utils.ProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    TypeProductRepository typeProductRepository;

    public ProductResponse saveProduct(ProductRequest productRequest){
        TypeProduct typeProduct = typeProductRepository.findById(productRequest.getTypeId()).get();
        Product product = ProductConvert.toEntity(productRequest, typeProduct);
        return  ProductConvert.toResponse(productRepository.save(product));
    }

    public List<ProductResponse> getAllProduct(Integer typeProduct){
        if(typeProduct != null){
            return getAllByTypeProduct(typeProduct);
        }
        return ProductConvert.toResponseList(productRepository.findAll());
    }

    public List<ProductResponse> getAllByTypeProduct(Integer typeProduct){
        return ProductConvert.toResponseList(productRepository.findProductByType(typeProduct));
    }
}
