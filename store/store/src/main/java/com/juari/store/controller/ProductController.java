package com.juari.store.controller;

import com.juari.store.dto.ProductCatalogResponse;
import com.juari.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductCatalogResponse>> getCatalog(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    //HOLA
}
