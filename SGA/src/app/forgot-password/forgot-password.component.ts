import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { BsModalRef } from 'ngx-bootstrap/modal';
import {AuthenticationService} from '../services/authentication.service';
import {API_BASE_URL} from '../constants/constants';
import {Router} from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
  onClose:Subject<boolean>;

  constructor(private modalRef:BsModalRef, private authService:AuthenticationService, private router:Router) { }

  ngOnInit() {
    this.onClose = new Subject();
  }

  onResetPassword(data) {
    let url = API_BASE_URL+"/forgotPassword";
    this.authService.postResource(url,data)
      .subscribe(data=>{

      },err=>{
        console.log(err);
      })
  }
}
