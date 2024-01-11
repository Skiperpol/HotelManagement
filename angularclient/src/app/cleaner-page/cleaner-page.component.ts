import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cleaner-page',
  templateUrl: './cleaner-page.component.html',
  styleUrls: ['./cleaner-page.component.css']
})
export class CleanerPageComponent implements OnInit{
  constructor(private router: Router){
    console.log(this.router.getCurrentNavigation()?.extras.state);
  }
  public employeeId: number = 1;
  ngOnInit(): void {
    this.employeeId = history.state;
    console.log(this.employeeId);
  }
}
