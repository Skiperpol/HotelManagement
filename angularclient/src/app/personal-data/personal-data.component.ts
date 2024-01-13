import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-personal-data',
  templateUrl: './personal-data.component.html',
  styleUrls: ['./personal-data.component.css']
})
export class PersonalDataComponent implements OnInit{
  constructor(private router: Router){
    if(this.router.getCurrentNavigation()?.extras.state == undefined)
      router.navigateByUrl('login');
  }
  public employeeId: any;
  ngOnInit(): void {
  this.employeeId = history.state;
  console.log(this.employeeId.id);
  }

}
