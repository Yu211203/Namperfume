package com.ex.namperfume.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    UUID product_id;
    String productName;
    String description;
    String fragranceFamily;
    String style;
    BrandResponse brand;
    ProductTypeResponse type;
    List<ProductSizeResponse> productSizes;
}
