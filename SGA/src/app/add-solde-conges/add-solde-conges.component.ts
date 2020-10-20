import { Component, OnInit } from '@angular/core';
import {ParamService} from '../services/param.service';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Agent} from '../../Model/agent';
import {Annee} from '../../Model/annee';
import {EtatService} from '../services/etat.service';
import {API_BASE_ETAT_ABSENCE_URL} from '../constants/constants';

@Component({
  selector: 'app-add-solde-conges',
  templateUrl: './add-solde-conges.component.html',
  styleUrls: ['./add-solde-conges.component.css']
})
export class AddSoldeCongesComponent implements OnInit {
  agents: Observable<Agent[]>;
  annees: Observable<Annee[]>;
  soldes: Object;

  constructor(private etatService:EtatService, private paramService:ParamService, private router:Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData(){
    this.paramService.getAgentsList()
      .subscribe(data=>{
        this.agents = data;
      },err=>{
        console.log(err)
      });

    this.paramService.getAnneesList()
      .subscribe(data=>{
        this.annees = data;
      },err=>{
        console.log(err);
      })
  }

  onSaveSolde(data) {
    let url = API_BASE_ETAT_ABSENCE_URL+"/saveSoldeConge";
    this.etatService.postResource(url,data)
      .subscribe(data=>{
        this.soldes = data;
      },err=>{
        console.log(err);
      })
  }
}
