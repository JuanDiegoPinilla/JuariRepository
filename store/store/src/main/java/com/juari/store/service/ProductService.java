package com.juari.store.service;

import com.juari.store.dto.ProductCatalogResponse;
import com.juari.store.dto.ProductDetailResponse;
import com.juari.store.mapper.ProductMapper;
import com.juari.store.model.entity.Product;
import com.juari.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    public List<ProductCatalogResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toCatalogResponse)
                .toList();
    }

    @Override
    public ProductDetailResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Product not found with id: " + id
                ));

        return productMapper.toProductDetailResponse(product);
    }


}
