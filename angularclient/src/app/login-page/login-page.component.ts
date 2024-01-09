import { Component } from '@angular/core';
import { HotelService } from '../service/hotel.service';
import { EmployeeLoginDto } from '../model/employeeLoginDto/employeeLoginDto';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {
    public errorMessage: string = "";
    public loginDto: EmployeeLoginDto = {
      empLogin: "",
      empPassword: ""
    }

    constructor(
      private hotelService: HotelService,
      private router: Router
    ) {}


  onSubmit(form: NgForm){
    this.hotelService.login(this.loginDto).subscribe(
      (results: {job: string, empID: any}) => {
        confirm("Logging in successfull. Welcome " + results?.job + " " + results?.empID);
        this.router.navigateByUrl("/" + results.job, {state: results.empID});
      },
      (error) => {
        let errorMessageJSON: string = JSON.stringify(error);
        let key = "error";
        let index = errorMessageJSON.indexOf(key);
        this.errorMessage = errorMessageJSON.substring(index+8, errorMessageJSON.length-2);
        confirm(this.errorMessage);
      }
    );
  }
}
