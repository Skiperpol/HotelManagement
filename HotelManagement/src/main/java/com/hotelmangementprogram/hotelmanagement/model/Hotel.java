package com.hotelmangementprogram.hotelmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hotel {
    private ArrayList<String> pendingOrders;
    private double balance;
    private Date currentDate;

    /**
     * Initializes the whole HotelManagement system:
     * Connects to the database and loads all the necessary data.
     */
    public void setUp(){

    }

    /**
     * Shuts down the whole HotelManagement system:
     * Saves all the data to the database and disconnects.
     */
    public void shutdown(){

    }

    /**
     * Simulates the transition to the next day.
     */
    public void nextDay(){

    }
}
