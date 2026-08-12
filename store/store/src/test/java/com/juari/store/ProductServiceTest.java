package com.juari.store;


import com.juari.store.dto.ProductDetailResponse;
import com.juari.store.mapper.ProductMapper;
import com.juari.store.model.entity.Product;
import com.juari.store.repository.ProductRepository;
import com.juari.store.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldGetProductById() {

        Long productId = 1L;

        Product product = mock(Product.class);

        ProductDetailResponse expectedResponse = new ProductDetailResponse(
                1L,
                "Laptop Lenovo IdeaPad",
                "Laptop ideal para trabajo, estudio y entretenimiento.",
                new BigDecimal("2499900.00"),
                10,
                "https://images.example.com/laptop-lenovo.jpg",
                "Tecnología"
        );

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        when(productMapper.toProductDetailResponse(product))
                .thenReturn(expectedResponse);


        ProductDetailResponse response =
                productService.getProductById(productId);


        assertEquals(expectedResponse, response);

        verify(productRepository).findById(productId);
        verify(productMapper).toProductDetailResponse(product);
    }

    @Test
    void shouldThrowExceptionWhenProductDoesNotExist() {


        Long productId = 999L;

        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());


        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productService.getProductById(productId)
        );

        assertEquals(
                "Product not found with id: 999",
                exception.getMessage()
        );

        verify(productRepository).findById(productId);
        verifyNoInteractions(productMapper);
    }
}
