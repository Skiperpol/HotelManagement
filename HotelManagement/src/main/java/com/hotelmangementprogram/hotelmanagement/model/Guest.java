package com.hotelmangementprogram.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "guest")
//@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Guest extends People{

    private Integer additionalCharges;
    private Integer roomNumber;
    private String checkInDate;
    private String checkOutDate;

    public Guest(Integer additionalCharges, Integer roomNumber, String checkInDate, String checkOutDate){
        this.additionalCharges=additionalCharges;
        this.roomNumber=roomNumber;
        this.checkInDate=checkInDate;
        this.checkOutDate=checkOutDate;
    }
    public void askToCleanRoom(){

    }
}