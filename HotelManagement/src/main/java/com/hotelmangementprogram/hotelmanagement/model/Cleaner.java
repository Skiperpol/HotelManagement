package com.hotelmangementprogram.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "CLEANER")
@Getter
@NoArgsConstructor
@DiscriminatorValue("CLEANER")
public class Cleaner extends Employee implements Serializable {
    @Column(name = "commission")
    private Float commission;

    public Cleaner(Long employeeId, String firstName, String lastName, String pesel, String phoneNumber, String emailAddress, Job job, Float commission){
        super(employeeId, firstName, lastName, pesel, phoneNumber, emailAddress, job);
        this.commission = commission;
    }

    public void cleanRoom(){

    }


    public void calculatePaycheck(){

    }
   public void showUncleanedRooms(){
        //{wyświetla pokoje o składowej
       //
       //        isClean==false};
   }
}
