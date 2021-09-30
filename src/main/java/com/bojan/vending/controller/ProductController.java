package com.bojan.vending.controller;

import com.bojan.vending.dto.ProductDto;
import com.bojan.vending.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/product")
@Tag(name = "product", description = "The Product API")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Fetch a product by ID", description = "Fetch a product by ID", tags = {"product"})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto fetchProduct(@PathVariable int id) {
        return productService.findProductById(id);

    }

    @RolesAllowed("SELLER")
    @Operation(summary = "Add a new product", description = "Add a new product", tags = { "product" })
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto addProduct(@NotNull @Valid @RequestBody ProductDto productDto, Principal principal) {
        String username = principal != null ? principal.getName() : null;

        return productService.create(productDto, username);
    }

    @RolesAllowed("SELLER")
    @Operation(summary = "Delete a product by ID", description = "Delete a product by ID", tags = { "product" })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);

    }
}
