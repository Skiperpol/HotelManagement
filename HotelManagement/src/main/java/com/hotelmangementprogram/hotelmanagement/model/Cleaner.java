package com.hotelmangementprogram.hotelmanagement.model;

import com.hotelmangementprogram.hotelmanagement.PaycheckStrategy.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity(name = "CLEANER")
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("CLEANER")
public class Cleaner extends Employee implements Serializable {
    @Column(name = "commission")
    private Float commission;



    public Cleaner(Long employeeId, String firstName, String lastName, String pesel, String phoneNumber, String emailAddress, Job job, Float commission){
        super(employeeId, firstName, lastName, pesel, phoneNumber, emailAddress, job);
        this.commission = commission;
        paycheck=new Commision(commission);
    }

    public void cleanRoom(){

    }

   public static List<Room> showUncleanedRooms(List<Room> allRooms){
        return allRooms.stream()
                .filter(Room::isRoomIsClean).toList();
   }
}
