package com.nawid.EMS.controller.v1;

import com.nawid.EMS.dto.request.EmployeeAddressRequest;
import com.nawid.EMS.dto.response.EmployeeAddressResponse;
import com.nawid.EMS.security.user.CustomUserPrincipal;
import com.nawid.EMS.service.EmployeeAddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/addresses")
public class EmployeeAddressController {

    private final EmployeeAddressService addressService;

    public EmployeeAddressController(EmployeeAddressService addressService) {
        this.addressService = addressService;
    }

    // =========================
    // EMPLOYEE ENDPOINTS
    // =========================

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PostMapping("/me")
    public ResponseEntity<EmployeeAddressResponse> createMyAddress(
            @Valid @RequestBody EmployeeAddressRequest request,
            Authentication authentication) {

        CustomUserPrincipal principal =
                (CustomUserPrincipal) authentication.getPrincipal();

        return new ResponseEntity<>(
                addressService.create(principal.getEmployeeId(), request),
                HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PutMapping("/me/{id}")
    public ResponseEntity<EmployeeAddressResponse> updateMyAddress(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeAddressRequest request,
            Authentication authentication) {

        CustomUserPrincipal principal =
                (CustomUserPrincipal) authentication.getPrincipal();

        return ResponseEntity.ok(
                addressService.update(id, principal.getEmployeeId(), request));
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @DeleteMapping("/me/{id}")
    public ResponseEntity<String> deleteMyAddress(
            @PathVariable Long id,
            Authentication authentication) {

        CustomUserPrincipal principal =
                (CustomUserPrincipal) authentication.getPrincipal();

        return ResponseEntity.ok(
                addressService.delete(id, principal.getEmployeeId()));
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/me")
    public ResponseEntity<List<EmployeeAddressResponse>> getMyAddresses(
            Authentication authentication) {

        CustomUserPrincipal principal =
                (CustomUserPrincipal) authentication.getPrincipal();

        return ResponseEntity.ok(
                addressService.getByEmployee(principal.getEmployeeId()));
    }

    // =========================
    // ADMIN ENDPOINTS
    // =========================

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeAddressResponse> createForEmployee(
            @PathVariable Long employeeId,
            @Valid @RequestBody EmployeeAddressRequest request) {

        return new ResponseEntity<>(
                addressService.create(employeeId, request),
                HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeAddressResponse>> getEmployeeAddresses(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                addressService.getByEmployee(employeeId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{id}/employee/{employeeId}")
    public ResponseEntity<String> deleteForEmployee(
            @PathVariable Long id,
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                addressService.delete(id, employeeId));
    }
}