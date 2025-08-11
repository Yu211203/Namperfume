package com.ex.namperfume.controller;

import com.ex.namperfume.dto.request.BrandRequest;
import com.ex.namperfume.dto.response.ApiResponse;
import com.ex.namperfume.dto.response.BrandResponse;
import com.ex.namperfume.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
@Slf4j
public class BrandController {
    private final BrandService brandService;

    @PostMapping
    public ApiResponse<BrandResponse> createBrand(@Valid @RequestBody BrandRequest request){
        ApiResponse<BrandResponse> api = new ApiResponse<>();
        api.setResult(brandService.createBrand(request));
        return api;
    }

    @GetMapping
    public ApiResponse<List<BrandResponse>> getBrands(){
        return ApiResponse.<List<BrandResponse>>builder()
                .result(brandService.getBrands())
                .build();
    }

    @GetMapping("/{brand_id}")
    public ApiResponse<BrandResponse> getBrand(@PathVariable("brand_id") UUID brand_id){
        return ApiResponse.<BrandResponse>builder()
                .result(brandService.getBrand(brand_id))
                .build();
    }

    @DeleteMapping("/{brand_id}")
    public String deleteBrand(@PathVariable("brand_id") UUID brand_id){
        brandService.deleteBrand(brand_id);
        return "Brand delete successfully!!";
    }

    @PatchMapping("/{brand_id}")
    public ApiResponse<BrandResponse> updateBrandName(@PathVariable("brand_id")UUID brand_id, @RequestBody String newBrandName){
        return ApiResponse.<BrandResponse>builder()
                .result(brandService.updateBrandName(brand_id, newBrandName))
                .build();
    }
}
