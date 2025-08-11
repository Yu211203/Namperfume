package com.ex.namperfume.service;

import com.ex.namperfume.dto.request.SizeRequest;
import com.ex.namperfume.dto.response.SizeResponse;
import com.ex.namperfume.entity.Size;
import com.ex.namperfume.mapper.SizeMapper;
import com.ex.namperfume.repository.SizeRepository;
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
public class SizeService {
    SizeRepository sizeRepository;
    SizeMapper sizeMapper;

    public SizeResponse createSize (SizeRequest request){
        Size size = sizeMapper.toSize(request);
        try {
            size = sizeRepository.save(size);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return sizeMapper.toSizeResponse(size);
    }

    public SizeResponse getSize (UUID size_id){
        return sizeMapper.toSizeResponse(sizeRepository.findById(size_id).orElseThrow(
                ()-> new RuntimeException("Size not found in db")
        ));
    }

    public List<SizeResponse> getSizes (){
        return sizeRepository.findAll().stream().map(sizeMapper::toSizeResponse).toList();
    }

    public void deleteSize(UUID size_id){
        sizeRepository.deleteById(size_id);
    }

    public SizeResponse updateSizeValue(UUID size_id, int newSizeValue){
        Size size = sizeRepository.findById(size_id).orElseThrow();
        size.setSize_value(newSizeValue);
        Size updateSize = sizeRepository.save(size);
        return sizeMapper.toSizeResponse(updateSize);
    }

}
