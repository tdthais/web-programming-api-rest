package com.ada.AccessoriesMarketplace.service;

import com.ada.AccessoriesMarketplace.controller.dto.TypeProductRequest;
import com.ada.AccessoriesMarketplace.controller.dto.TypeProductResponse;
import com.ada.AccessoriesMarketplace.model.TypeProduct;
import com.ada.AccessoriesMarketplace.repository.TypeProductRepository;
import com.ada.AccessoriesMarketplace.utils.TypeProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeProductService {

    @Autowired
    TypeProductRepository typeProductRepository;

    public List<TypeProductResponse> getAllTypeProducts(){
        return TypeProductConvert.toResponseList(typeProductRepository.findAll());
    }

    public TypeProductResponse saveTypeProduct(TypeProductRequest typeProductRequest){
        TypeProduct typeProduct = typeProductRepository.save(
                TypeProductConvert.toEntity(typeProductRequest)
        );
        return TypeProductConvert.toResponse(typeProduct);
    }

    public void deleteTypeProduct(Integer id){
        typeProductRepository.deleteById(id);
    }
}
