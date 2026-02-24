package com.nawid.EMS.repo;


import com.nawid.EMS.entity.EmployeeAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeAddressRepository
        extends JpaRepository<EmployeeAddress, Long> {

    List<EmployeeAddress> findByEmployeeId(Long employeeId);

    Optional<EmployeeAddress> findByEmployeeIdAndIsPrimaryTrue(Long employeeId);
}