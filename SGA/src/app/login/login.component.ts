import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../services/authentication.service';
import {Router} from '@angular/router';
import {FACEBOOK_AUTH_URL, GOOGLE_AUTH_URL} from '../constants/constants';
import {Utilisateur} from '../../Model/utilisateur';
import {ForgotPasswordComponent} from '../forgot-password/forgot-password.component';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  utilisateur:Utilisateur = new Utilisateur();
  public modalRef:BsModalRef;
  errorMessage:string;

  constructor(private authService:AuthenticationService, private router:Router, private modalService:BsModalService) { }

  ngOnInit() {
  }

  onLogin(data){
    this.authService.login(data)
      .subscribe(resp=>{
        let jwt = resp.body['accessToken'];
        this.authService.saveToken(jwt);
        this.router.navigateByUrl("/");
      }, err=>{
        this.errorMessage = 'Adresse email ou mot de passe incorrect';
      })
  }

  goToFacebookUrl(){
    window.location.href = FACEBOOK_AUTH_URL;
  }

  goToGoogleUrl(){
    window.location.href = GOOGLE_AUTH_URL;
  }

  open(){
    this.modalRef = this.modalService.show(ForgotPasswordComponent);
    this.modalRef.content.onClose.subscribe(result => {
      console.log('results', result);
    })
  }
}
