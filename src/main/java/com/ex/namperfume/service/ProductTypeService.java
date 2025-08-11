package com.ex.namperfume.service;

import com.ex.namperfume.dto.request.ProductTypeRequest;
import com.ex.namperfume.dto.response.ProductTypeResponse;
import com.ex.namperfume.entity.ProductType;
import com.ex.namperfume.mapper.ProductTypeMapper;
import com.ex.namperfume.repository.ProductTypeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductTypeService {
    ProductTypeRepository repository;
    ProductTypeMapper mapper;

    public ProductTypeResponse createProductType (ProductTypeRequest request){
        ProductType type = mapper.toProductType(request);
        try {
            type = repository.save(type);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return mapper.toProductTypeResponse(type);

    }

    public ProductTypeResponse getProductType(UUID type_id){
        return mapper.toProductTypeResponse(repository.findById(type_id).orElseThrow(
                ()-> new RuntimeException("Not found product type")
        ));
    }

    public List<ProductTypeResponse> getProductTypes (){
        return repository.findAll().stream().map(mapper::toProductTypeResponse).toList();
    }

    public void deleteProductType(UUID type_id){
        repository.deleteById(type_id);
    }

    public ProductTypeResponse updateTypeName(UUID type_id, String newTypeName){
        ProductType type = repository.findById(type_id).orElseThrow(()-> new RuntimeException("Not found type with id: "+type_id));

        type.setType_name(newTypeName);
        ProductType updateType = repository.save(type);

        return mapper.toProductTypeResponse(updateType);
    }
}
