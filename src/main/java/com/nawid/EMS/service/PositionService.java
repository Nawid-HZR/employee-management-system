package com.nawid.EMS.service;


import com.nawid.EMS.dto.request.PositionRequest;
import com.nawid.EMS.dto.response.PositionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PositionService {

    PositionResponse create(PositionRequest request);

    PositionResponse getById(Long id);

    Page<PositionResponse> getAll(Pageable pageable);

    PositionResponse update(Long id, PositionRequest request);

    String delete(Long id);
}