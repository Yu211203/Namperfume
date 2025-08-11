package com.ex.namperfume.service;

import com.ex.namperfume.dto.request.ProductSizeRequest;
import com.ex.namperfume.dto.response.ProductSizeResponse;
import com.ex.namperfume.entity.ProductSize;
import com.ex.namperfume.entity.Size;
import com.ex.namperfume.mapper.ProductSizeMapper;
import com.ex.namperfume.repository.ProductSizeRepository;
import com.ex.namperfume.repository.SizeRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductSizeService {
    ProductSizeRepository repository;
    ProductSizeMapper mapper;
    SizeRepository sizeRepository;

    public ProductSizeResponse createProductSize(ProductSizeRequest request){
        Size size = sizeRepository.findById(request.getSize_id()).orElseThrow(()-> new RuntimeException("Not found size with id: "+request.getSize_id()));

        ProductSize productSize = mapper.toProductSize(request);
        productSize.setSize(size);

        try {
            productSize = repository.save(productSize);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return mapper.toProductSizeResponse(productSize);
    }

    public ProductSizeResponse getProductSize(UUID product_size_id){
        ProductSize productSize = repository.findById(product_size_id).orElseThrow(()-> new RuntimeException("Not found product size with id: "+product_size_id));
        return mapper.toProductSizeResponse(productSize);
    }

    public List<ProductSizeResponse> getProductSizes(){
        return repository.findAll().stream().map(mapper::toProductSizeResponse).collect(Collectors.toList());
    }

    @Transactional
    public void deleteProductSize(UUID product_size_id){
        if(!repository.existsById(product_size_id))
            throw new RuntimeException("Not found product size with id"+product_size_id);

        repository.deleteById(product_size_id);
    }
}
