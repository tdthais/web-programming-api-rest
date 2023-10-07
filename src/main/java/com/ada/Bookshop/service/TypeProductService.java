package com.ada.Bookshop.service;

import com.ada.Bookshop.controller.dto.TypeProductRequest;
import com.ada.Bookshop.controller.dto.TypeProductResponse;
import com.ada.Bookshop.model.TypeProduct;
import com.ada.Bookshop.repository.TypeProductRepository;
import com.ada.Bookshop.utils.TypeProductConvert;
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
