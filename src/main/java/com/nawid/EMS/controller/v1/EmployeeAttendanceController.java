package com.nawid.EMS.controller.v1;

import com.nawid.EMS.dto.response.AttendanceResponse;
import com.nawid.EMS.security.user.CustomUserPrincipal;
import com.nawid.EMS.service.EmployeeAttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendance")
public class EmployeeAttendanceController {

    private final EmployeeAttendanceService attendanceService;

    public EmployeeAttendanceController(
            EmployeeAttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PostMapping("/check-in")
    public ResponseEntity<AttendanceResponse> checkIn(
            Authentication authentication) {

        CustomUserPrincipal principal =
                (CustomUserPrincipal) authentication.getPrincipal();

        return ResponseEntity.ok(
                attendanceService.checkIn(principal.getEmployeeId())
        );
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PostMapping("/check-out")
    public ResponseEntity<AttendanceResponse> checkOut(
            Authentication authentication) {

        CustomUserPrincipal principal =
                (CustomUserPrincipal) authentication.getPrincipal();

        return ResponseEntity.ok(
                attendanceService.checkOut(principal.getEmployeeId())
        );
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/me")
    public ResponseEntity<List<AttendanceResponse>> getMyAttendance(
            Authentication authentication) {

        CustomUserPrincipal principal =
                (CustomUserPrincipal) authentication.getPrincipal();

        return ResponseEntity.ok(
                attendanceService.getMyAttendance(principal.getEmployeeId())
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<AttendanceResponse>> getAll() {
        return ResponseEntity.ok(attendanceService.getAll());
    }
}