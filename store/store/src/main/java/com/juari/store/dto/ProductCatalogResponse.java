package com.juari.store.dto;

import java.math.BigDecimal;

public record ProductCatalogResponse(Long id, String name,
                                     BigDecimal price,String imageUrl){
}
