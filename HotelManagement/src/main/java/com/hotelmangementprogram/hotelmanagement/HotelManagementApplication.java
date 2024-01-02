package com.hotelmangementprogram.hotelmanagement;

import com.hotelmangementprogram.hotelmanagement.controller.HotelController;
import com.hotelmangementprogram.hotelmanagement.model.*;
import com.hotelmangementprogram.hotelmanagement.service.HotelService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HotelManagementApplication {
	public static ArrayList<Menu> pendingOrders = new ArrayList<>();
	public static double balance;
	public static LocalDate currentDate;


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


	public static void main(String[] args) {
		SpringApplication.run(HotelManagementApplication.class, args);


		HotelService hotel = new HotelService();
		hotel.nextDay();
	}

}
