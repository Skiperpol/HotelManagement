import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cook-page',
  templateUrl: './cook-page.component.html',
  styleUrls: ['./cook-page.component.css']
})
export class CookPageComponent implements OnInit{
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
