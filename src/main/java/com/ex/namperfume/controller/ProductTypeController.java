package com.ex.namperfume.controller;

import com.ex.namperfume.dto.request.ProductTypeRequest;
import com.ex.namperfume.dto.response.ApiResponse;
import com.ex.namperfume.dto.response.ProductTypeResponse;
import com.ex.namperfume.service.ProductTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/types")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductTypeController {

    ProductTypeService productTypeService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<ProductTypeResponse> createProductType(@RequestBody ProductTypeRequest request){
        ApiResponse<ProductTypeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productTypeService.createProductType(request));
        return apiResponse;
    }

    @GetMapping("/{type_id}")
    public ApiResponse<ProductTypeResponse> getProductType(@PathVariable("type_id")UUID type_id){
        return ApiResponse.<ProductTypeResponse>builder()
                .result(productTypeService.getProductType(type_id))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ProductTypeResponse>> getProductTypes (){
        return ApiResponse.<List<ProductTypeResponse>>builder()
                .result(productTypeService.getProductTypes())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{type_id}")
    public ResponseEntity<ApiResponse<Void>> deleteProductType(@PathVariable("type_id") UUID type_id){
        productTypeService.deleteProductType(type_id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{type_id}")
    public ApiResponse<ProductTypeResponse> updateTypeName(@PathVariable("type_id") UUID type_id, @RequestBody String newTypeName){
        return ApiResponse.<ProductTypeResponse>builder()
                .result(productTypeService.updateTypeName(type_id, newTypeName))
                .build();
    }

}
