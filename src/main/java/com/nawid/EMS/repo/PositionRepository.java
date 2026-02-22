package com.nawid.EMS.repo;




import com.nawid.EMS.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {

    boolean existsByTitle(String title);
}