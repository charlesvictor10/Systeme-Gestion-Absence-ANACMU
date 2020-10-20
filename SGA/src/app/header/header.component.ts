import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../services/authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  utilisateur:Object;

  constructor(private authService:AuthenticationService, private router:Router) { }

  ngOnInit() {
    this.currentUser();
  }

  currentUser(){
    this.authService.currentUser()
      .subscribe(data=>{
        this.utilisateur = data;
      },err=>{
        console.log(err);
      })
  }

  logOut(){
    this.authService.logout();
    this.router.navigateByUrl("/login");
  }

  editPassword() {
    this.router.navigateByUrl("/editPassword");
  }
}
