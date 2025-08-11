package com.ex.namperfume.controller;

import com.ex.namperfume.dto.request.SizeRequest;
import com.ex.namperfume.dto.response.ApiResponse;
import com.ex.namperfume.dto.response.SizeResponse;
import com.ex.namperfume.service.SizeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sizes")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SizeController {
    SizeService sizeService;

    @PostMapping
    public ApiResponse<SizeResponse> createSize (@RequestBody SizeRequest request){
        ApiResponse<SizeResponse> api = new ApiResponse<>();
        api.setResult(sizeService.createSize(request));
        return api;
    }

    @GetMapping
    public ApiResponse<List<SizeResponse>> getSizes (){
        return ApiResponse.<List<SizeResponse>>builder()
                .result(sizeService.getSizes())
                .build();
    }

    @GetMapping("/{size_id}")
    public ApiResponse<SizeResponse> getSize(@PathVariable("size_id")UUID size_id){
        return ApiResponse.<SizeResponse>builder()
                .result(sizeService.getSize(size_id))
                .build();
    }

    @DeleteMapping("/{size_id}")
    public String deleteSize(@PathVariable("size_id") UUID size_id){
        sizeService.deleteSize(size_id);
        return "Size delete successfully";
    }
}
