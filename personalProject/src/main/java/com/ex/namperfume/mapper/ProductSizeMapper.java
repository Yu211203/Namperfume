package com.ex.namperfume.mapper;

import com.ex.namperfume.dto.request.ProductSizeRequest;
import com.ex.namperfume.dto.response.ProductSizeResponse;
import com.ex.namperfume.entity.ProductSize;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SizeMapper.class})
public interface ProductSizeMapper {

    @Mapping(target = "size", source = "size")
    ProductSizeResponse toProductSizeResponse(ProductSize productSize);

    // Bỏ qua liên kết đến product và size khi chuyển từ request về entity
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "size", ignore = true)
    @Mapping(target = "product_size_id", ignore = true)
    ProductSize toProductSize(ProductSizeRequest request);

}
