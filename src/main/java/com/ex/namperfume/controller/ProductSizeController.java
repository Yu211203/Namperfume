package com.ex.namperfume.controller;

import com.ex.namperfume.dto.request.ProductSizeRequest;
import com.ex.namperfume.dto.response.ApiResponse;
import com.ex.namperfume.dto.response.ProductSizeResponse;
import com.ex.namperfume.service.ProductSizeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productSizes")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductSizeController {

    ProductSizeService productSizeService;

    @PostMapping
    public ApiResponse<ProductSizeResponse> createProductSize(@RequestBody ProductSizeRequest request){
        ApiResponse<ProductSizeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productSizeService.createProductSize(request));
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<ProductSizeResponse>> getProductSizes(){
        return ApiResponse.<List<ProductSizeResponse>>builder()
                .result(productSizeService.getProductSizes())
                .build();
    }

    @GetMapping("/{product_size_id}")
    public ApiResponse<ProductSizeResponse> getProductSize(@PathVariable("product_size_id")UUID product_size_id){
        return ApiResponse.<ProductSizeResponse>builder()
                .result(productSizeService.getProductSize(product_size_id))
                .build();
    }

    @DeleteMapping("/{product_size_id}")
    public String deleteProductSize(@PathVariable("product_size_id") UUID product_size_id){
        productSizeService.deleteProductSize(product_size_id);
        return "Product size deleted successfully";
    }
}
