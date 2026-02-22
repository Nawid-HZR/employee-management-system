package com.nawid.EMS.service.impl;


import com.nawid.EMS.dto.request.DepartmentRequest;
import com.nawid.EMS.dto.response.DepartmentResponse;
import com.nawid.EMS.entity.Department;
import com.nawid.EMS.exception.BadRequestException;
import com.nawid.EMS.exception.ResourceNotFoundException;
import com.nawid.EMS.repo.DepartmentRepository;
import com.nawid.EMS.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper mapper; // inject general mapper

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    @Override
    public DepartmentResponse create(DepartmentRequest request) {
        if (departmentRepository.existsByName(request.getName())) {
            throw new BadRequestException("Department already exists");
        }

        Department department = new Department();
        department.setName(request.getName());
        department.setDescription(request.getDescription());

        Department savedDepartment = departmentRepository.save(department);
        return mapper.map(savedDepartment, DepartmentResponse.class);
    }

    @Override
    public DepartmentResponse getById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        return mapper.map(department, DepartmentResponse.class);
    }

    @Override
    public Page<DepartmentResponse> getAll(Pageable pageable) {
        return departmentRepository.findAll(pageable)
                .map(dep -> mapper.map(dep, DepartmentResponse.class));
    }

    @Override
    public DepartmentResponse update(Long id, DepartmentRequest request) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        department.setName(request.getName());
        department.setDescription(request.getDescription());

        Department updatedDepartment = departmentRepository.save(department);
        return mapper.map(updatedDepartment, DepartmentResponse.class);
    }

    @Override
    public String delete(Long id) {
        departmentRepository.deleteById(id);
        return "Deleted successfully";
    }
}