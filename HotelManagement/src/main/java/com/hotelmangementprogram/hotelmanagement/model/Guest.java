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
    private Integer roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Guest(Long personId, String firstName, String lastName, String pesel, String phoneNumber, String emailAddress, Float additionalCharges, Integer roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
        super(personId, firstName, lastName, pesel, phoneNumber, emailAddress);
        this.additionalCharges = additionalCharges;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public void askToCleanRoom(){

    }
}