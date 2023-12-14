package com.hotelmangementprogram.hotelmanagement.repository;

import com.hotelmangementprogram.hotelmanagement.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
