package com.juari.store.service;

import com.juari.store.dto.ProductCatalogResponse;

import java.util.List;

public interface IProductService {

    public List<ProductCatalogResponse> getAllProducts();
}
