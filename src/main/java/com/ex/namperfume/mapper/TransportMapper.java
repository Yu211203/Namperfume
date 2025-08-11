package com.ex.namperfume.mapper;

import com.ex.namperfume.dto.request.TransportRequest;
import com.ex.namperfume.dto.response.TransportResponse;
import com.ex.namperfume.entity.Transport;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransportMapper {
    Transport toTransport (TransportRequest request);
    TransportResponse toTransportResponse(Transport transport);
}
