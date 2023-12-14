package com.hotelmangementprogram.hotelmanagement.repository;

import com.hotelmangementprogram.hotelmanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
