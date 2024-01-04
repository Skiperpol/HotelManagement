package com.hotelmangementprogram.hotelmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GuestDto {
    private String firstName;
    private String lastName;
    private String pesel;
    private String phoneNumber;
    private String emailAddress;
}
