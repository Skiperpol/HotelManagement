package com.hotelmangementprogram.hotelmanagement.model;

import com.hotelmangementprogram.hotelmanagement.HotelManagementApplication;
import com.hotelmangementprogram.hotelmanagement.PaycheckStrategy.*;
import com.hotelmangementprogram.hotelmanagement.repository.RoomRepository;
import com.hotelmangementprogram.hotelmanagement.service.HotelService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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
        paycheck=new Salary(salary);
    }

    public void assignRoom(){
        //przypisuje pokój gościom i wypełnia szczegóły pobytu
    }
    public static List<Room> showVacantRooms(List<Room> allRooms){
         return allRooms.stream()
                 .filter(Room::isRoomIsEmpty).toList();
    }

    public void registerNewGuest()
    {
        //dodanie do bazy danych gości
        //
        //        nowego gościa
    }




}
