package com.hotelmangementprogram.hotelmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class EmployeeDto {
    private String empLogin;
    private String empPassword;
    private String firstName;
    private String lastName;
    private String pesel;
    private String phoneNumber;
    private String emailAddress;
    private Float salaryIfApplicable;
}
