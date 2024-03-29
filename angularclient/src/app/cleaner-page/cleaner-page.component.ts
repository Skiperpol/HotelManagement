import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cleaner-page',
  templateUrl: './cleaner-page.component.html',
  styleUrls: ['./cleaner-page.component.css']
})
export class CleanerPageComponent implements OnInit{
  constructor(private router: Router){
    if(this.router.getCurrentNavigation()?.extras.state == undefined)
      router.navigateByUrl('login');
  }

  public employeeId = {id: ""};
  ngOnInit(): void {
    this.employeeId = history.state;
    console.log(this.employeeId.id);
  }

  public showPersonalData(): void {
    this.router.navigateByUrl("/personal-data", {state: {id: this.employeeId.id}});
  }


}
