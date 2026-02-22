package com.nawid.EMS.service;


import com.nawid.EMS.dto.request.DepartmentRequest;
import com.nawid.EMS.dto.response.DepartmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {

    DepartmentResponse create(DepartmentRequest request);
    Page<DepartmentResponse> getAll(Pageable pageable);
    DepartmentResponse getById(Long id);
    DepartmentResponse update(Long id, DepartmentRequest request);
    String delete(Long id);
}