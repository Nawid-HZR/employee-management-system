package com.nawid.EMS.service;


import com.nawid.EMS.dto.request.EmployeeRequest;
import com.nawid.EMS.dto.response.EmployeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    EmployeeResponse create(EmployeeRequest request);

    EmployeeResponse getById(Long id);

    Page<EmployeeResponse> getAll(Pageable pageable);

    EmployeeResponse update(Long id, EmployeeRequest request);

    String delete(Long id);
}