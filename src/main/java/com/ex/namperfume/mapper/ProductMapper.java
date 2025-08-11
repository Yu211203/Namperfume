package com.ex.namperfume.mapper;

import com.ex.namperfume.dto.request.ProductRequest;
import com.ex.namperfume.dto.response.ProductResponse;
import com.ex.namperfume.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
    uses = {BrandMapper.class, ProductTypeMapper.class, ProductSizeMapper.class})
public interface ProductMapper {

    //--Chuyển đổi từ Entity sang Response DTO--
    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "productSizes", source = "productSizes")
    ProductResponse toProductResponse(Product product);

    //--Chuyển đổi từ Request DTO sang Entity--
    //Ignore các trường liên kết, chúng ta sẽ xử lý trong service
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "productSizes", ignore = true)
    @Mapping(target = "product_id", ignore = true)
    Product toProduct (ProductRequest request);

    //--Update entity từ DTO
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "productSizes", ignore = true)
    @Mapping(target = "product_id", ignore = true)
    void updateProductFromDto(ProductRequest request, @MappingTarget Product product);

}
