package com.nawid.EMS.service.impl;

import com.nawid.EMS.dto.request.EmployeeSalaryRequest;
import com.nawid.EMS.dto.response.EmployeeSalaryResponse;
import com.nawid.EMS.entity.Employee;
import com.nawid.EMS.entity.EmployeeSalary;
import com.nawid.EMS.exception.ResourceNotFoundException;
import com.nawid.EMS.repo.EmployeeRepository;
import com.nawid.EMS.repo.EmployeeSalaryRepository;
import com.nawid.EMS.service.EmployeeSalaryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSalaryServiceImpl implements EmployeeSalaryService {

    private final EmployeeSalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeSalaryServiceImpl(EmployeeSalaryRepository salaryRepository,
                                     EmployeeRepository employeeRepository,
                                     ModelMapper mapper) {
        this.salaryRepository = salaryRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public EmployeeSalaryResponse create(EmployeeSalaryRequest request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        // Business Rule: Only one active salary
        salaryRepository.findByEmployeeIdAndIsActiveTrue(employee.getId())
                .ifPresent(activeSalary -> {
                    activeSalary.setActive(false);
                    salaryRepository.save(activeSalary);
                });

        EmployeeSalary salary = new EmployeeSalary();
        salary.setEmployee(employee);
        salary.setBasicSalary(request.getBasicSalary());
        salary.setAllowance(request.getAllowance());
        salary.setEffectiveFrom(request.getEffectiveFrom());
        salary.setActive(true);

        EmployeeSalary saved = salaryRepository.save(salary);

        return mapToResponse(saved);
    }

    @Override
    public List<EmployeeSalaryResponse> getByEmployee(Long employeeId) {

        return salaryRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public EmployeeSalaryResponse getActiveSalary(Long employeeId) {

        EmployeeSalary salary = salaryRepository
                .findByEmployeeIdAndIsActiveTrue(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Active salary not found"));

        return mapToResponse(salary);
    }

    @Override
    public String delete(Long id) {

        EmployeeSalary salary = salaryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Salary not found"));

        salaryRepository.delete(salary);

        return "Deleted successfully";
    }

    private EmployeeSalaryResponse mapToResponse(EmployeeSalary salary) {

        EmployeeSalaryResponse response =
                mapper.map(salary, EmployeeSalaryResponse.class);

        response.setEmployeeId(salary.getEmployee().getId());
        response.setEmployeeName(salary.getEmployee().getFullName());
        response.setTotalSalary(
                salary.getBasicSalary() + salary.getAllowance()
        );

        return response;
    }
}
