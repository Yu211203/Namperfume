package com.ex.namperfume.mapper;

import com.ex.namperfume.dto.request.BrandRequest;
import com.ex.namperfume.dto.response.BrandResponse;
import com.ex.namperfume.entity.Brand;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    Brand toBrand(BrandRequest request);
    BrandResponse toBrandResponse (Brand brand);}
