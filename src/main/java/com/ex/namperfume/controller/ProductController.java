package com.ex.namperfume.controller;

import com.ex.namperfume.dto.request.ProductRequest;
import com.ex.namperfume.dto.response.ApiResponse;
import com.ex.namperfume.dto.response.ProductResponse;
import com.ex.namperfume.entity.Product;
import com.ex.namperfume.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductController {

    ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@RequestBody ProductRequest request){
        ApiResponse<ProductResponse> api = new ApiResponse<>();
        api.setResult(productService.createProduct(request));
        return api;
    }

    @GetMapping
    public ApiResponse<List<ProductResponse>> getAllProducts(){
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getAllProducts())
                .build();
    }

    @GetMapping("/productId/{product_id}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable("product_id")UUID product_id){
        return ApiResponse.<ProductResponse>builder()
                .result(productService.getProductById(product_id))
                .build();
    }

    @GetMapping("/brandId/{brand_id}")
    public ApiResponse<List<ProductResponse>> getProductByBrand(@PathVariable("brand_id")UUID brand_id){
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getProductsByBrand(brand_id))
                .build();
    }

    @GetMapping("/typeId/{type_id}")
    public ApiResponse<List<ProductResponse>> getProductByType(@PathVariable("type_id")UUID type_id){
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getProductsByType(type_id))
                .build();
    }

    @GetMapping("/productName/{product_name}")
    public ApiResponse<List<ProductResponse>> getProductByName(@PathVariable("product_name") String product_name){
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getProductByName(product_name))
                .build();
    }

    @GetMapping("/productStyle/{style}")
    public ApiResponse<List<ProductResponse>> getProductByBrand(@PathVariable("style")String style){
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getProductsByStyle(style))
                .build();
    }

    @DeleteMapping("/{product_id}")
    public String deleteProduct(@PathVariable("product_id") UUID product_id){
        productService.deleteProduct(product_id);
        return "Product deleted successfully";
    }

    @PutMapping("/{product_id}")
    public ApiResponse<ProductResponse> updateProductById(@PathVariable("product_id") UUID product_id,
                                                      @Valid @RequestBody ProductRequest request){
        return ApiResponse.<ProductResponse>builder()
                .result(productService.updateProductById(product_id, request))
                .build();
    }

    @PatchMapping("/{product_id}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable("product_id")UUID product_id, @Valid @RequestBody ProductRequest request){
        Product existingProduct = productService.findProductById(product_id);
        return ApiResponse.<ProductResponse>builder()
                .result(productService.updateProduct(request, existingProduct))
                .build();
    }
}
