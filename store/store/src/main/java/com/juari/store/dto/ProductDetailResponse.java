package com.juari.store.dto;

import java.math.BigDecimal;

public record ProductDetailResponse(Long id,
                                    String name,
                                    String description,
                                    BigDecimal price,
                                    Integer stock,
                                    String imageUrl,
                                    String category) {
}
