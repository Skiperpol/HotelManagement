import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cook-page',
  templateUrl: './cook-page.component.html',
  styleUrls: ['./cook-page.component.css']
})
export class CookPageComponent implements OnInit{
  constructor(private router: Router){
    console.log(this.router.getCurrentNavigation()?.extras.state);
  }
  public employeeId: number = 1;
  ngOnInit(): void {
    this.employeeId = history.state;
    console.log(this.employeeId);
  }
}
