import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../services/authentication.service';
import {Agent} from '../../Model/agent';
import {EtatService} from '../services/etat.service';

@Component({
  selector: 'app-personnelles',
  templateUrl: './personnelles.component.html',
  styleUrls: ['./personnelles.component.css']
})
export class PersonnellesComponent implements OnInit {
  utilisateur:Object;
  agent:Agent;
  soldeConge:Object;

  constructor(private authService:AuthenticationService, private etatService:EtatService) { }

  ngOnInit() {
    this.currentUser();
  }

  currentUser(){
    this.authService.currentUser()
      .subscribe(data=>{
        this.utilisateur = data;
        this.agent = data['agent'].id;
        this.etatService.getSoldeCongeParAgent(this.agent)
          .subscribe(data=>{
            this.soldeConge = data;
          })
      },err=>{
        console.log(err);
      })
  }
}
