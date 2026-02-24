package com.nawid.EMS.service;



import com.nawid.EMS.dto.request.EmployeeAddressRequest;
import com.nawid.EMS.dto.response.EmployeeAddressResponse;

import java.util.List;


import java.util.List;

public interface EmployeeAddressService {

    EmployeeAddressResponse create(Long employeeId, EmployeeAddressRequest request);

    EmployeeAddressResponse update(Long id, Long employeeId, EmployeeAddressRequest request);

    List<EmployeeAddressResponse> getByEmployee(Long employeeId);

    String delete(Long id, Long employeeId);
}
