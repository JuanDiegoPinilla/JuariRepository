package com.juari.store.service;

import com.juari.store.dto.ProductCatalogResponse;
import com.juari.store.dto.ProductDetailResponse;

import java.util.List;

public interface IProductService {

    public List<ProductCatalogResponse> getAllProducts();

    public ProductDetailResponse getProductById(Long id);
}
