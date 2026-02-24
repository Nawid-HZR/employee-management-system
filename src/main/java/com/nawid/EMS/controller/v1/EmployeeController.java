package com.nawid.EMS.controller.v1;


import com.nawid.EMS.dto.request.EmployeeRequest;
import com.nawid.EMS.dto.response.EmployeeResponse;
import com.nawid.EMS.security.user.CustomUserPrincipal;
import com.nawid.EMS.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody EmployeeRequest request) {
        return new ResponseEntity<>(employeeService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                employeeService.getAll(PageRequest.of(page, size))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {

        return ResponseEntity.ok(employeeService.update(id, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.delete(id));
    }
    @GetMapping("/me")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<EmployeeResponse> getMyProfile(Authentication authentication) {

        CustomUserPrincipal principal =
                (CustomUserPrincipal) authentication.getPrincipal();

        EmployeeResponse response =
                employeeService.getById(principal.getEmployeeId());

        return ResponseEntity.ok(response);
    }


}