import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {Employee} from "../model/employee/employee";
import {HotelService} from "../service/hotel.service";
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit{
  public employeeToFireId = "";
  public hotelBalance = 0;
  public currentDate = "";
  public employee: Employee = {
    personId: 0,
    firstName: "",
    lastName: "",
    pesel: "",
    phoneNumber: "",
    emailAddress: "",
    job: "",
    salary: 0,
    commission: 0,
    hourlyWage: 0
  }


  constructor(private router: Router, private hotelService: HotelService){
    if(this.router.getCurrentNavigation()?.extras.state == undefined)
      router.navigateByUrl('login');
  }
  public employeeId: any;
  ngOnInit(): void {
    this.employeeId = history.state;
    console.log(this.employeeId.id);
  }


  // to poprawione ma Karol na swoim branchu 
  // Pod to tez trzeba bedzie dodac ngForm onSubmitHireEmployee
  public hire(employee: Employee):void{
    this.hotelService.saveEmployee(employee).subscribe(
      response => {
        confirm("pomyślnie dodano pracownika do bazy danych")
      },
      error=>{
        let errorMessageJSON: string = JSON.stringify(error);
        let key = "error";
        let index = errorMessageJSON.indexOf(key);
        let errorMessage = errorMessageJSON.substring(index, errorMessageJSON.length);
        confirm(errorMessage);
      }
    )
  }


  onSubmitFireEmployee(form: NgForm){
    this.hotelService.deleteEmployee(this.employeeToFireId).subscribe(
      response => {
        confirm("pomyślnie usunięto pracownika z bazy danych")
      },
      error=>{
        let errorMessageJSON: string = JSON.stringify(error);
        let key = "error";
        let index = errorMessageJSON.indexOf(key);
        let errorMessage = errorMessageJSON.substring(index, errorMessageJSON.length);
        confirm(errorMessage);
      }
    )
  }

  public getHotelInfo(): void{
    this.hotelService.getCurrentDate().subscribe(
      (date: string) => {
        this.currentDate = date;
      },
      (error: any) => {
        let errorMessageJSON: string = JSON.stringify(error);
        let key = "error";
        let index = errorMessageJSON.indexOf(key);
        let errorMessage = errorMessageJSON.substring(index, errorMessageJSON.length);
        confirm(errorMessage);
      }
    )
    this.hotelService.getBalance().subscribe(
      (balance: number) => {
        this.hotelBalance = balance;
      }
      ,(error: any) => {
        let errorMessageJSON: string = JSON.stringify(error);
        let key = "error";
        let index = errorMessageJSON.indexOf(key);
        let errorMessage = errorMessageJSON.substring(index, errorMessageJSON.length);
        confirm(errorMessage);
      }
    )
    }
  
  // JEST PROBLEM Z EDIT EMPLOYEE W BACKENDZIE BO TO PRZYJMUJE EmployeeDto a nie Employee
  // Employeedto ma pole login i password a Employee nie ma
  onSubmitEditEmployee(form: NgForm){
    this.hotelService.updateEmployee(this.employee.personId, this.employee.job, this.employee).subscribe(
      response => {
        confirm("pomyślnie zaktualizowano dane pracownika")
      },
      error=>{
        let errorMessageJSON: string = JSON.stringify(error);
        let key = "error";
        let index = errorMessageJSON.indexOf(key);
        let errorMessage = errorMessageJSON.substring(index, errorMessageJSON.length);
        confirm(errorMessage);
      }
    )



  }

}

