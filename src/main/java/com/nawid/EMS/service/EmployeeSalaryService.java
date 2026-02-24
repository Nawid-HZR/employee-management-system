package com.nawid.EMS.service;

import com.nawid.EMS.dto.request.EmployeeSalaryRequest;
import com.nawid.EMS.dto.response.EmployeeSalaryResponse;

import java.util.List;

public interface EmployeeSalaryService {

    EmployeeSalaryResponse create(EmployeeSalaryRequest request);

    List<EmployeeSalaryResponse> getByEmployee(Long employeeId);

    EmployeeSalaryResponse getActiveSalary(Long employeeId);

    String delete(Long id);
}