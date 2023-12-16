package com.hotelmangementprogram.hotelmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Getter
@NoArgsConstructor
public class Receptionist extends Employee{
//    @Column(name = "salary")
    private Float salary;

    public Receptionist(Long employeeId, String firstName, String lastName, String pesel, String phoneNumber, String emailAddress, Job job, Float commission){
        super(employeeId, firstName, lastName, pesel, phoneNumber, emailAddress, job);
        this.salary = salary;
        //Lombok does not have an annotation for superclasses sadly
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
