package com.ex.namperfume.mapper;

import com.ex.namperfume.dto.request.ImageRequest;
import com.ex.namperfume.dto.response.ImageResponse;
import com.ex.namperfume.entity.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    Image toImage (ImageRequest request);
    ImageResponse toImageResponse (Image image);
}
