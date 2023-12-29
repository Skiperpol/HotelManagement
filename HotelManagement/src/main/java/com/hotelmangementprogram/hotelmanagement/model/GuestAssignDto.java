package com.hotelmangementprogram.hotelmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class GuestAssignDto {
    private String guestIds;
    private Long roomNumber;
    private String checkInDate;
    private String checkOutDate;
}
