package com.nawid.EMS.service.impl;

import com.nawid.EMS.dto.request.EmployeeRequest;
import com.nawid.EMS.dto.response.EmployeeResponse;
import com.nawid.EMS.entity.Department;
import com.nawid.EMS.entity.Employee;
import com.nawid.EMS.entity.Position;
import com.nawid.EMS.exception.BadRequestException;
import com.nawid.EMS.exception.ResourceNotFoundException;
import com.nawid.EMS.repo.DepartmentRepository;
import com.nawid.EMS.repo.EmployeeRepository;
import com.nawid.EMS.repo.PositionRepository;
import com.nawid.EMS.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository,
                               PositionRepository positionRepository,
                               ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
        this.mapper = mapper;
    }

    @Override
    public EmployeeResponse create(EmployeeRequest request) {

        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        Position position = positionRepository.findById(request.getPositionId())
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));

        Employee employee = new Employee();
        employee.setFullName(request.getFullName());
        employee.setEmail(request.getEmail());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setHireDate(request.getHireDate());
        employee.setDepartment(department);
        employee.setPosition(position);

        Employee saved = employeeRepository.save(employee);

        return mapToResponse(saved);
    }

    @Override
    public EmployeeResponse getById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return mapToResponse(employee);
    }

    @Override
    public Page<EmployeeResponse> getAll(Pageable pageable) {

        return employeeRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    public EmployeeResponse update(Long id, EmployeeRequest request) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        Position position = positionRepository.findById(request.getPositionId())
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));

        employee.setFullName(request.getFullName());
        employee.setEmail(request.getEmail());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setHireDate(request.getHireDate());
        employee.setDepartment(department);
        employee.setPosition(position);

        return mapToResponse(employeeRepository.save(employee));
    }

    @Override
    public String delete(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employeeRepository.delete(employee);

        return "Deleted successfully";
    }

    private EmployeeResponse mapToResponse(Employee employee) {

        EmployeeResponse response = mapper.map(employee, EmployeeResponse.class);

        response.setDepartmentName(employee.getDepartment().getName());
        response.setPositionTitle(employee.getPosition().getTitle());

        return response;
    }
}