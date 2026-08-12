package com.juari.store.service;


import com.juari.store.dto.ProductCatalogResponse;
import com.juari.store.dto.ProductDetailResponse;
import com.juari.store.mapper.ProductMapper;
import com.juari.store.model.entity.Product;
import com.juari.store.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
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
    void shouldGetAllProducts() {

        //Productos feka
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);

        ProductCatalogResponse response1 = new ProductCatalogResponse(
                1L,
                "Laptop Lenovo IdeaPad",
                new BigDecimal("2499900.00"),
                "https://images.example.com/laptop-lenovo.jpg"
        );

        ProductCatalogResponse response2 = new ProductCatalogResponse(
                2L,
                "Laptop Lenovo IdeaPad2.0",
                new BigDecimal("3000000.00"),
                "https://images.example.com/laptop-lenovo-2.0.jpg"
        );

        when(productRepository.findAll())
                .thenReturn(List.of(product1, product2));

        when(productMapper.toCatalogResponse(product1))
                .thenReturn(response1);

        when(productMapper.toCatalogResponse(product2))
                .thenReturn(response2);

        List<ProductCatalogResponse> result = productService.getAllProducts();

        assertEquals(2, result.size());
        assertEquals(response1, result.get(0));
        assertEquals(response2, result.get(1));

        verify(productRepository).findAll();

        verify(productMapper).toCatalogResponse(product1);
        verify(productMapper).toCatalogResponse(product2);
    }

    @Test
    void shouldFindProductsByName(){
        Product product = mock(Product.class);

        ProductCatalogResponse expectedResponse = new ProductCatalogResponse(
                1L,
                "Laptop Lenovo IdeaPad",
                new BigDecimal("2499900.00"),
                "https://images.example.com/laptop-lenovo.jpg"
        );

        when(productRepository.findByNameContainingIgnoreCase("comp"))
                .thenReturn(List.of(product));

        when(productMapper.toCatalogResponse(product))
                .thenReturn(expectedResponse);

        List<ProductCatalogResponse> result = productService.getProductsByName("comp");

        assertEquals(1, result.size());
        assertEquals(expectedResponse, result.get(0));

        verify(productRepository).findByNameContainingIgnoreCase("comp");
        verify(productMapper).toCatalogResponse(product);
    }

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
