package com.juari.store.mapper;

import com.juari.store.dto.CategoryResponse;
import com.juari.store.dto.ProductCatalogResponse;
import com.juari.store.model.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductCatalogResponse toCatalogResponse(Product product){
        return new ProductCatalogResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }
}
