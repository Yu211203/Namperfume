package com.ex.namperfume.service;

import com.ex.namperfume.dto.request.TransportRequest;
import com.ex.namperfume.dto.response.TransportResponse;
import com.ex.namperfume.entity.Transport;
import com.ex.namperfume.mapper.TransportMapper;
import com.ex.namperfume.repository.TransportRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TransportService {
    TransportRepository transportRepository;
    TransportMapper transportMapper;

    public TransportResponse createTransport(TransportRequest request){
        Transport transport = transportMapper.toTransport(request);
        try{
            transport = transportRepository.save(transport);
        }catch (RuntimeException e){
            throw new RuntimeException("Error");
        }

        return transportMapper.toTransportResponse(transport);
    }

    public List<TransportResponse> getTransports(){
        return transportRepository.findAll().stream().map(transportMapper::toTransportResponse).collect(Collectors.toList());
    }

    public TransportResponse getTransport(UUID transport_id){
        Transport transport = transportRepository.findById(transport_id).orElseThrow(()->new RuntimeException("Not found transport with id: "+ transport_id));

        return transportMapper.toTransportResponse(transport);
    }

    public void deleteTransport(UUID transport_id){
        transportRepository.deleteById(transport_id);
    }
}
