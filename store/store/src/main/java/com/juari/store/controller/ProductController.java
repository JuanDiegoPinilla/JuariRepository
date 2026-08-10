package com.juari.store.controller;

import com.juari.store.dto.ProductCatalogResponse;
import com.juari.store.dto.ProductDetailResponse;
import com.juari.store.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Operation(
            summary = "Obtener detalle de un producto",
            description = "Obtiene la información detallada de un producto mediante su ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Producto encontrado correctamente"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Producto no encontrado"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
}
