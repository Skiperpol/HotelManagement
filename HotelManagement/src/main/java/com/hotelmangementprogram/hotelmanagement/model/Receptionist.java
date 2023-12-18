package com.hotelmangementprogram.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "RECEPTIONIST")
@Getter
@NoArgsConstructor
@DiscriminatorValue("RECEPTIONIST")
public class Receptionist extends Employee implements Serializable {
    @Column(name = "salary")
    private Float salary;

    public Receptionist(Long employeeId, String firstName, String lastName, String pesel, String phoneNumber, String emailAddress, Job job, Float salary){
        super(employeeId, firstName, lastName, pesel, phoneNumber, emailAddress, job);
        this.salary = salary;
    }
    @Override
    public void calculatePaycheck(){

    }

    public void assignRoom(){
        //przypisuje pokój gościom i wypełnia szczegóły pobytu
    }
    public void showVacantRooms(){
       // pokazuje liste pustych pokoi}
    }

    public void registerNewGuest()
    {
        //dodanie do bazy danych gości
        //
        //        nowego gościa
    }




}
