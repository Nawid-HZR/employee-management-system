package com.nawid.EMS.service;





import com.nawid.EMS.dto.response.AttendanceResponse;

import java.util.List;

public interface EmployeeAttendanceService {

    AttendanceResponse checkIn(Long employeeId);

    AttendanceResponse checkOut(Long employeeId);

    List<AttendanceResponse> getMyAttendance(Long employeeId);

    List<AttendanceResponse> getAll();
}