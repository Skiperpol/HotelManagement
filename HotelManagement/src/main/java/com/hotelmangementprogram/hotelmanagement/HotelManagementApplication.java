package com.hotelmangementprogram.hotelmanagement;

import com.hotelmangementprogram.hotelmanagement.controller.HotelController;
import com.hotelmangementprogram.hotelmanagement.model.*;
import com.hotelmangementprogram.hotelmanagement.service.HotelService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.*;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Double.parseDouble;

@SpringBootApplication
public class HotelManagementApplication {
	public static ArrayList<Menu> pendingOrders = new ArrayList<>();
	public static double balance;
	public static LocalDate currentDate;


	/**
	 * Initializes the whole HotelManagement system:
	 * Connects to the database and loads all the necessary data.
	 */
	public static void setUp(){

		try (FileReader fileReader = new FileReader("balance.txt"); Scanner scanner=new Scanner(fileReader)) {
			while(scanner.hasNextLine()){
				try {
					balance = Double.parseDouble(scanner.nextLine());
				}catch(NumberFormatException e){
					System.out.println("Wykryto błędnie zapisane dane w pliku Balance.txt. Wymaga on naprawy.");
				}
			}


		} catch (FileNotFoundException e) {
			System.out.println("Nie znaleziono pliku do odczytu danych: " + e.getMessage());
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println("Błąd przy odczycie pliku: " + e.getMessage());
			e.printStackTrace();
		}

		try (FileInputStream fileInputStream = new FileInputStream("currentDate.ser");
			 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

			currentDate = (LocalDate) objectInputStream.readObject();

		} catch (FileNotFoundException e) {
			System.out.println("Nie znaleziono pliku do odczytu danych: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Błąd przy odczycie pliku: " + e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Nie można znaleźć klasy podczas deserializacji obiektu: " + e.getMessage());
			e.printStackTrace();
		}

	}


	public static void main(String[] args) {
		SpringApplication.run(HotelManagementApplication.class, args);
		setUp();
	}

}
