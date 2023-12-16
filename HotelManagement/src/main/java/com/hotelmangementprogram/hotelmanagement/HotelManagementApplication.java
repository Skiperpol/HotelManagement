package com.hotelmangementprogram.hotelmanagement;

import com.hotelmangementprogram.hotelmanagement.model.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class HotelManagementApplication {
	public static List<Menu> pendingOrders;
	public static float balance;

	public static void main(String[] args) {
		//balance is to be imported from a file here and saved upon exiting the app
		SpringApplication.run(HotelManagementApplication.class, args);
	}

}
