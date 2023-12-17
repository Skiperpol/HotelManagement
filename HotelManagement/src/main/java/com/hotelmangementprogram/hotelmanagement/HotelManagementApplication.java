package com.hotelmangementprogram.hotelmanagement;

import com.hotelmangementprogram.hotelmanagement.model.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class HotelManagementApplication {
	private ArrayList<Menu> pendingOrders;
	private double balance;
	private LocalDate currentDate;

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
