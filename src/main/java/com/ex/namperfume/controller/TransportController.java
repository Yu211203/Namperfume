package com.ex.namperfume.controller;

import com.ex.namperfume.dto.request.TransportRequest;
import com.ex.namperfume.dto.response.ApiResponse;
import com.ex.namperfume.dto.response.TransportResponse;
import com.ex.namperfume.service.TransportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transports")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransportController {

    TransportService transportService;

    @PostMapping
    public ApiResponse<TransportResponse> createTransport(@RequestBody TransportRequest request){
        ApiResponse<TransportResponse> api = new ApiResponse<>();
        api.setResult(transportService.createTransport(request));
        return api;
    }

    @GetMapping
    public ApiResponse<List<TransportResponse>> getTransports(){
        return ApiResponse.<List<TransportResponse>>builder()
                .result(transportService.getTransports())
                .build();
    }

    @GetMapping("/{transport_id}")
    public ApiResponse<TransportResponse> getTransport(@PathVariable("transport_id")UUID transport_id){
        return ApiResponse.<TransportResponse>builder()
                .result(transportService.getTransport(transport_id))
                .build();
    }

    @DeleteMapping("/{transport_id}")
    public String deleteTransport(@PathVariable("transport_id")UUID transport_id){
        transportService.deleteTransport(transport_id);
        return "Transport deleted successfully";
    }

}
