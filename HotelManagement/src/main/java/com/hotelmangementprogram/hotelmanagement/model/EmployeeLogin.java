package com.hotelmangementprogram.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employeelogin")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLogin{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private Long employeeId;
    @Column(name = "EmpLogin")
    private String empLogin;
    @Column(name = "EmpPassword")
    private String empPassword;
}
