package com.nawid.EMS.repo;




import com.nawid.EMS.entity.EmployeeSalary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalary, Long> {

    List<EmployeeSalary> findByEmployeeId(Long employeeId);

    Optional<EmployeeSalary> findByEmployeeIdAndIsActiveTrue(Long employeeId);
}