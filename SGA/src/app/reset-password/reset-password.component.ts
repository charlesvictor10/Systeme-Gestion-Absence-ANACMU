import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../services/authentication.service';
import {Router} from '@angular/router';
import {API_BASE_URL} from '../constants/constants';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  utilisateur:Object;

  constructor(private authService:AuthenticationService, private router:Router) { }

  ngOnInit() {
  }

  onResetPassword(data) {
    let url = API_BASE_URL+"/resetPassword";
    this.authService.postResource(url,data)
      .subscribe(data=>{
        this.utilisateur = data;
      },err=>{
        console.log(err);
      })
  }
}
