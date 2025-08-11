package com.ex.namperfume.service;

import com.ex.namperfume.dto.request.BrandRequest;
import com.ex.namperfume.dto.response.BrandResponse;
import com.ex.namperfume.entity.Brand;
import com.ex.namperfume.mapper.BrandMapper;
import com.ex.namperfume.repository.BrandRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrandService {
    BrandMapper brandMapper;
    BrandRepository brandRepository;

    public BrandResponse createBrand (BrandRequest request){
        Brand brand = brandMapper.toBrand(request);

        try{
            brand = brandRepository.save(brand);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return brandMapper.toBrandResponse(brand);
    }

    public BrandResponse getBrand(UUID brand_id){
        return brandRepository.findById(brand_id).map(brandMapper::toBrandResponse)
                .orElseThrow(()-> new RuntimeException("Brand not found"));
    }

    public List<BrandResponse> getBrands (){
        return brandRepository.findAll().stream().map(brandMapper::toBrandResponse).toList();
    }

    public void deleteBrand (UUID brand_id){
        brandRepository.deleteById(brand_id);
    }

    public BrandResponse updateBrandName(UUID brand_id, String newBrandName){
        Brand brand = brandRepository.findById(brand_id).orElseThrow(()-> new RuntimeException("Not found brand with id: "+ brand_id));

        brand.setBrand_name(newBrandName);
        Brand updateBrand = brandRepository.save(brand);
        return brandMapper.toBrandResponse(updateBrand);
    }
}
