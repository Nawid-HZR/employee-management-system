package com.nawid.EMS.service.impl;


import com.nawid.EMS.dto.request.EmployeeAddressRequest;
import com.nawid.EMS.dto.response.EmployeeAddressResponse;
import com.nawid.EMS.entity.Employee;
import com.nawid.EMS.entity.EmployeeAddress;
import com.nawid.EMS.exception.BadRequestException;
import com.nawid.EMS.exception.ResourceNotFoundException;
import com.nawid.EMS.repo.EmployeeAddressRepository;
import com.nawid.EMS.repo.EmployeeRepository;
import com.nawid.EMS.service.EmployeeAddressService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeAddressServiceImpl implements EmployeeAddressService {

    private final EmployeeAddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeAddressServiceImpl(
            EmployeeAddressRepository addressRepository,
            EmployeeRepository employeeRepository,
            ModelMapper mapper) {

        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public EmployeeAddressResponse create(Long employeeId,
                                          EmployeeAddressRequest request) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        if (Boolean.TRUE.equals(request.getPrimary())) {

            addressRepository.findByEmployeeIdAndIsPrimaryTrue(employeeId)
                    .ifPresent(existing -> {
                        existing.setPrimary(false);
                        addressRepository.save(existing);
                    });
        }

        EmployeeAddress address = new EmployeeAddress();
        address.setEmployee(employee);
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setCountry(request.getCountry());
        address.setPrimary(request.getPrimary());

        return mapToResponse(addressRepository.save(address));
    }

    @Override
    public EmployeeAddressResponse update(Long id,
                                          Long employeeId,
                                          EmployeeAddressRequest request) {

        EmployeeAddress address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        // 🔐 Ownership validation
        if (!address.getEmployee().getId().equals(employeeId)) {
            throw new BadRequestException("You cannot modify this address");
        }

        if (Boolean.TRUE.equals(request.getPrimary())) {

            addressRepository.findByEmployeeIdAndIsPrimaryTrue(employeeId)
                    .ifPresent(existing -> {
                        if (!existing.getId().equals(id)) {
                            existing.setPrimary(false);
                            addressRepository.save(existing);
                        }
                    });
        }

        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setCountry(request.getCountry());
        address.setPrimary(request.getPrimary());

        return mapToResponse(addressRepository.save(address));
    }

    @Override
    public List<EmployeeAddressResponse> getByEmployee(Long employeeId) {

        return addressRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public String delete(Long id, Long employeeId) {

        EmployeeAddress address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        if (!address.getEmployee().getId().equals(employeeId)) {
            throw new BadRequestException("You cannot delete this address");
        }

        addressRepository.delete(address);
        return "Deleted successfully";
    }

    private EmployeeAddressResponse mapToResponse(EmployeeAddress address) {

        EmployeeAddressResponse response =
                mapper.map(address, EmployeeAddressResponse.class);

        response.setEmployeeId(address.getEmployee().getId());
        response.setEmployeeName(address.getEmployee().getFullName());

        return response;
    }
}