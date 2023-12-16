package com.hotelmangementprogram.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "guest")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Guest extends People{

    private Float additionalCharges;
    private Integer roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public void askToCleanRoom(){

    }
}