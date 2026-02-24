package com.nawid.EMS.controller.v1;

import com.nawid.EMS.dto.request.EmployeeSalaryRequest;
import com.nawid.EMS.dto.response.EmployeeSalaryResponse;
import com.nawid.EMS.service.EmployeeSalaryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salaries")
public class EmployeeSalaryController {

    private final EmployeeSalaryService salaryService;

    public EmployeeSalaryController(EmployeeSalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EmployeeSalaryResponse> create(
            @Valid @RequestBody EmployeeSalaryRequest request) {

        return new ResponseEntity<>(salaryService.create(request),
                HttpStatus.CREATED);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeSalaryResponse>> getByEmployee(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                salaryService.getByEmployee(employeeId)
        );
    }

    @GetMapping("/employee/{employeeId}/active")
    public ResponseEntity<EmployeeSalaryResponse> getActiveSalary(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                salaryService.getActiveSalary(employeeId)
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(salaryService.delete(id));
    }
}