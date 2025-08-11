package com.ex.namperfume.mapper;

import com.ex.namperfume.dto.request.ProductTypeRequest;
import com.ex.namperfume.dto.response.ProductTypeResponse;
import com.ex.namperfume.entity.ProductType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductTypeMapper {
    ProductType toProductType (ProductTypeRequest request);
    ProductTypeResponse toProductTypeResponse (ProductType productType);
}
