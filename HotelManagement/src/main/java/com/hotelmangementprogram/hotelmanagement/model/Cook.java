package com.hotelmangementprogram.hotelmanagement.model;

import com.hotelmangementprogram.hotelmanagement.HotelManagementApplication;
import com.hotelmangementprogram.hotelmanagement.PaycheckStrategy.*;
import com.hotelmangementprogram.hotelmanagement.controller.HotelController;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(name = "COOK")
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("COOK")
public class Cook extends Employee implements Serializable {
    @Column(name = "salary")
    private Float salary;
    @Column(name = "commission")
    private Float commission;
    //private HotelController hotelController;

    public Cook(Long employeeId, String firstName, String lastName, String pesel, String phoneNumber, String emailAddress, Job job, Float commission, Float salary){
        super(employeeId, firstName, lastName, pesel, phoneNumber, emailAddress, job);
        this.commission = commission;
        this.salary = salary;
        paycheck=new SalaryAndCommision(salary,commission);
    }

// DOES NOT WORK - CRASHES THE APP
//    public void completeOrder(OrderDto orderDto,int orderId){
//        hotelController.completeOrder(orderDto, orderId, getPersonId());
//    }

    public static ArrayList<Menu> showOrders(){
        return HotelManagementApplication.pendingOrders;
        }
    }
