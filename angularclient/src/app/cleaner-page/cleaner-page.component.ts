import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cleaner-page',
  templateUrl: './cleaner-page.component.html',
  styleUrls: ['./cleaner-page.component.css']
})
export class CleanerPageComponent implements OnInit{
  constructor(private router: Router){
    console.log("CLEANER_PAGE_ID " + this.router.getCurrentNavigation()?.extras.state);
  }
  public employeeId: number = 1;
  ngOnInit(): void {
    this.employeeId = history.state;
    console.log(this.employeeId);
  }

  public showPersonalData(): void {
    this.router.navigateByUrl("/personal-data", {state: {employeeId: this.employeeId}});
  }


}
