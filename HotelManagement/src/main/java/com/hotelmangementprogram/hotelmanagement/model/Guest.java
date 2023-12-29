package com.hotelmangementprogram.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "guest")
@NoArgsConstructor
@Getter
public class Guest extends People{

    private Float additionalCharges;
    private Long roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Guest(Long personId, String firstName, String lastName, String pesel, String phoneNumber, String emailAddress, Float additionalCharges, Long roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
        super(personId, firstName, lastName, pesel, phoneNumber, emailAddress);
        this.additionalCharges = additionalCharges;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public void addAdditionalCharges(Float amount){
        if(additionalCharges == null)
            additionalCharges = 0f;
        this.additionalCharges += amount;
    }
}