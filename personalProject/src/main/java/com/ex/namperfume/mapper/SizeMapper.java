package com.ex.namperfume.mapper;

import com.ex.namperfume.dto.request.SizeRequest;
import com.ex.namperfume.dto.response.SizeResponse;
import com.ex.namperfume.entity.Size;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SizeMapper {
    Size toSize (SizeRequest request);
    SizeResponse toSizeResponse (Size size);
}
