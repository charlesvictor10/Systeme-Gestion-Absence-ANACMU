import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'SGA 2.0';
  bodyClasses = 'skin-blue sidebar-mini';
  bodyLog = "login-page";
  body: HTMLBodyElement = document.getElementsByTagName('body')[0];

  constructor(private authService:AuthenticationService){
  }

  ngOnInit() {
    this.body.classList.add('skin-blue');
    this.body.classList.add('sidebar-mini');
    this.body.classList.add('login-page');
    this.authService.loadToken();
  }

  isAuthenticated(){
     return this.authService.isAuthenticated();
  }

  isAdmin(){
    return this.authService.isAdmin();
  }

  isRH(){
    return this.authService.isRH();
  }

  isDG(){
    return this.authService.isDG();
  }

  isSH(){
    return this.authService.isSH();
  }

  isAgent(){
    return this.authService.isAgent();
  }
}
