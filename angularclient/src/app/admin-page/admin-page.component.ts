import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {Employee} from "../model/employee/employee";
import {HotelService} from "../service/hotel.service";
import { HttpStatusCode } from '@angular/common/http';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit{
  constructor(private router: Router, private hotelService: HotelService){
    if(this.router.getCurrentNavigation()?.extras.state == undefined)
      router.navigateByUrl('login');
  }
  public employeeId: any;
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
    hourlyWage: 0,
  }
  ngOnInit(): void {
    this.employeeId = history.state;
    console.log(this.employeeId.id);
  }

  public hire():void{
    this.hotelService.saveEmployee(this.employee).subscribe(
      (employee: Employee) => {
        confirm("pomyślnie dodano pracownika " +  this.employee.job + " do bazy danych. ID: " + this.employee.personId)
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
  public fire(employeeId: string):void{
    this.hotelService.deleteEmployee(employeeId).subscribe(
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
}
