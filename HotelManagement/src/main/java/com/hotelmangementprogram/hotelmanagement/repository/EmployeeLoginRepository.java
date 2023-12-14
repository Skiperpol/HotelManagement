package com.hotelmangementprogram.hotelmanagement.repository;

import com.hotelmangementprogram.hotelmanagement.model.EmployeeLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeLoginRepository extends JpaRepository<EmployeeLogin, Long> {
}
