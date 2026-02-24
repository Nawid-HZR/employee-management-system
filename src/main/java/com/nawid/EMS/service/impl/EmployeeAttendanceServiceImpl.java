package com.nawid.EMS.service.impl;

import com.nawid.EMS.dto.response.AttendanceResponse;
import com.nawid.EMS.entity.Employee;
import com.nawid.EMS.entity.EmployeeAttendance;
import com.nawid.EMS.exception.BadRequestException;
import com.nawid.EMS.exception.ResourceNotFoundException;
import com.nawid.EMS.repo.EmployeeAttendanceRepository;
import com.nawid.EMS.repo.EmployeeRepository;
import com.nawid.EMS.service.EmployeeAttendanceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class EmployeeAttendanceServiceImpl
        implements EmployeeAttendanceService {

    private final EmployeeAttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeAttendanceServiceImpl(
            EmployeeAttendanceRepository attendanceRepository,
            EmployeeRepository employeeRepository,
            ModelMapper mapper) {

        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public AttendanceResponse checkIn(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        LocalDate today = LocalDate.now();

        attendanceRepository.findByEmployeeIdAndDate(employeeId, today)
                .ifPresent(att -> {
                    throw new BadRequestException("Already checked in today");
                });

        EmployeeAttendance attendance = new EmployeeAttendance();
        attendance.setEmployee(employee);
        attendance.setDate(today);
        attendance.setCheckIn(LocalTime.now());
        attendance.setPresent(true);

        EmployeeAttendance saved = attendanceRepository.save(attendance);

        return mapToResponse(saved);
    }

    @Override
    public AttendanceResponse checkOut(Long employeeId) {

        LocalDate today = LocalDate.now();

        EmployeeAttendance attendance = attendanceRepository
                .findByEmployeeIdAndDate(employeeId, today)
                .orElseThrow(() -> new BadRequestException("You must check in first"));

        if (attendance.getCheckOut() != null) {
            throw new BadRequestException("Already checked out today");
        }

        attendance.setCheckOut(LocalTime.now());

        return mapToResponse(attendanceRepository.save(attendance));
    }

    @Override
    public List<AttendanceResponse> getMyAttendance(Long employeeId) {

        return attendanceRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<AttendanceResponse> getAll() {

        return attendanceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private AttendanceResponse mapToResponse(EmployeeAttendance attendance) {

        AttendanceResponse response =
                mapper.map(attendance, AttendanceResponse.class);

        response.setEmployeeId(attendance.getEmployee().getId());
        response.setEmployeeName(attendance.getEmployee().getFullName());

        return response;
    }
}