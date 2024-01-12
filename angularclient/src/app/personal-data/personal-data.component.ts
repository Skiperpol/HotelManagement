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

  constructor(private hotelService: HotelService, private router: Router){
    if(this.router.getCurrentNavigation()?.extras.state == undefined)
      router.navigateByUrl('login');
  }
  public employeeId = {id: ""};
  ngOnInit(): void {
    this.employeeId = history.state;
    console.log(this.employeeId.id);
    this.getEmployee();
  }

  public getEmployee(): void {
    this.hotelService.getEmployee(this.employeeId.id).subscribe(
      (employee: Employee) => {
        this.employee = employee;
         ("Employee found");
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
