import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-waiter-page',
  templateUrl: './waiter-page.component.html',
  styleUrls: ['./waiter-page.component.css']
})
export class WaiterPageComponent implements OnInit{
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
