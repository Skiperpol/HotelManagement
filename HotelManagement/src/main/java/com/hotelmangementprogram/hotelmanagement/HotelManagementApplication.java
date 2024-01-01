package com.hotelmangementprogram.hotelmanagement;

import com.hotelmangementprogram.hotelmanagement.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;

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

	/**
	 * Simulates the transition to the next day.
	 */
	public void nextDay(){
		this.currentDate = this.currentDate.plusDays(1);

//		TEST EMPLOYEE
//		Employee [] employee = new Employee[1];
//		employee[0] = new Admin(1L, "Adam", "Kowalski", "12345678901", "123456789", "adam.kowalski@email.com", Job.ADMIN, 5000.02f);
//		Admin.payoutPaycheck(employee[0]);

//		Trzeba wstawić wszystkich employee z bazy danych do listy employeesList
//		employeesList = wszyscy employee
//		if (this.currentDate.getDayOfMonth() == 11){
//			for (Employee employee:employeesList) {
//				Admin.payoutPaycheck(employee);
//			}
//		}


//		TEST ROOM
		Room [] rooms = new Room[1];
		rooms[0] = new Room(null, 208L, 400.0, true, true, RoomType.DOUBLEROOM, null);
//		TEST GUEST
		Guest [] guests = new Guest[1];
		guests[0] = new Guest(null, "Piotr", "Wiśniewski", "34567890123", "456789012", "piotr.wisniewski@email.com", 0F, 208L, LocalDate.of(2023, 11, 12), LocalDate.of(2024, 1, 1));
//		guestsList = wszyscy goście których checkoutDate != null
		for (Guest guest:guests) {
			if (guest.getCheckOutDate()==currentDate){
//				roomTemp = pokój o numerze równym numerze u guesta
//				balance += guest.getAdditionalCharges() + roomTemp.getRoomPrice;
//				roomTemp.setRoomIsEmpty = True;
//				roomTemp.isClean = False;
			}
		}
	}

	public static void main(String[] args) {SpringApplication.run(HotelManagementApplication.class, args);}

}
