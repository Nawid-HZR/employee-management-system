package com.nawid.EMS.repo;




import com.nawid.EMS.entity.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

public interface EmployeeAttendanceRepository
        extends JpaRepository<EmployeeAttendance, Long> {

    Optional<EmployeeAttendance> findByEmployeeIdAndDate(
            Long employeeId, LocalDate date);

    List<EmployeeAttendance> findByEmployeeId(Long employeeId);
}