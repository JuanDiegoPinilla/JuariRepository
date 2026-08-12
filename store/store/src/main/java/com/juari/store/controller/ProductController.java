package com.juari.store.controller;

import com.juari.store.dto.ProductCatalogResponse;
import com.juari.store.dto.ProductDetailResponse;
import com.juari.store.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "Obtener catalogo de productos",
            description = """
                    Lista todos los productos disponibles y 
                    los muestra como catalogo"""
    )
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

    @Operation(
            summary = "Filtrar productos por categoría",
            description = "Obtiene todos los productos pertenecientes a una categoría específica."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Productos filtrados correctamente"
    )
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductCatalogResponse>> getProductsByCategory(
            @Parameter(
                    description = "ID de la categoría",
                    example = "1"
            )
            @PathVariable Long categoryId) {

        return ResponseEntity.ok(
                productService.getProductsByCategory(categoryId)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductCatalogResponse>> getProductsByName(
            @RequestParam String name){
        if(name == null || name.isBlank()){
            return  ResponseEntity.ok(productService.getAllProducts());
        }
        return ResponseEntity.ok(productService.getProductsByName(name));
    }
}
