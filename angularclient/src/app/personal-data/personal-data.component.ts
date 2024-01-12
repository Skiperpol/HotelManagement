import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HotelService } from '../service/hotel.service';
import { Employee } from '../model/employee/employee';

@Component({
  selector: 'app-personal-data',
  templateUrl: './personal-data.component.html',
  styleUrls: ['./personal-data.component.css']
})
export class PersonalDataComponent implements OnInit{
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
  };
  public employeeId: number = 0;

  constructor(private hotelService: HotelService, private router: Router){
    console.log(this.router.getCurrentNavigation()?.extras.state);
  }
  
  ngOnInit(): void {
    this.getEmployee();
    const state = this.router.getCurrentNavigation()?.extras.state;
    if (state) {
      this.employeeId = state['employeeId'];
    }
    console.log("empId: " + this.employeeId);
  }
  
  public getEmployee(): void {
    this.hotelService.getEmployee(String(this.employeeId)).subscribe(
      (employee: Employee) => {
        confirm("Employee found");
        this.employee = employee;
      },
      (error) => {
        let errorMessageJSON: string = JSON.stringify(error);
        let key = "error";
        let index = errorMessageJSON.indexOf(key);
        let errorMessage = errorMessageJSON.substring(index+8, errorMessageJSON.length-2);
        confirm(errorMessage);
      }
    )
  }

}
