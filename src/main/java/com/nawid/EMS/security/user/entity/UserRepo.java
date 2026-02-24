package com.nawid.EMS.security.user.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
}
