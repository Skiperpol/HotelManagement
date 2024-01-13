import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { HeaderComponent } from './header/header.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { CookPageComponent } from './cook-page/cook-page.component';
import { CleanerPageComponent } from './cleaner-page/cleaner-page.component';
import { ReceptionistPageComponent } from './receptionist-page/receptionist-page.component';
import { WaiterPageComponent } from './waiter-page/waiter-page.component';
import { PersonalDataComponent } from './personal-data/personal-data.component';
import { HomePageComponent } from './home-page/home-page.component';
import { FormsModule } from '@angular/forms';
import { HttpBackend, HttpClient, HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    AdminPageComponent,
    HeaderComponent,
    LoginPageComponent,
    CookPageComponent,
    CleanerPageComponent,
    ReceptionistPageComponent,
    WaiterPageComponent,
    PersonalDataComponent,
    HomePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
