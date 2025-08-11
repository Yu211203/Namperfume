package com.ex.namperfume.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    String productName;
    String description;
    String fragranceFamily;
    String style;

    UUID brand_id;
    UUID type_id;
//    List<ImageRequest> images;
    List<ProductSizeRequest> productSizes;
}
