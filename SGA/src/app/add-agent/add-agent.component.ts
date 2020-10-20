import { Component, OnInit } from '@angular/core';
import {ParamService} from '../services/param.service';
import {Observable} from 'rxjs';
import {Civilite} from '../../Model/civilite';
import {Statut} from '../../Model/statut';
import {Entite} from '../../Model/entite';
import {Service} from '../../Model/service';
import {Fonction} from '../../Model/fonction';
import {API_BASE_URL} from '../constants/constants';
import {Router} from '@angular/router';
import {Agent} from '../../Model/agent';

@Component({
  selector: 'app-add-agent',
  templateUrl: './add-agent.component.html',
  styleUrls: ['./add-agent.component.css']
})
export class AddAgentComponent implements OnInit {
  civilites: Observable<Civilite[]>;
  statuts: Observable<Statut[]>;
  superviseurs: Observable<Agent[]>;
  fonctions: Observable<Fonction[]>;
  services: Observable<Service[]>;
  entites: Observable<Entite[]>;

  constructor(private paramService:ParamService, private router:Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData(){
    this.paramService.getCivilitesList()
      .subscribe(data=>{
          this.civilites = data;
      }, err=>{
        console.log(err);
      })


    this.paramService.getStatutsList()
      .subscribe(data=>{
        this.statuts = data;
      },err=>{
        console.log(err);
      })

    this.paramService.getEntitesList()
      .subscribe(data=>{
        this.entites = data;
      }, err=>{
        console.log(err);
      })

    this.paramService.getServicesList()
      .subscribe(data=>{
        this.services = data;
      },err=>{
        console.log(err);
      })

    this.paramService.getAgentsList()
      .subscribe(data=>{
        this.superviseurs = data;
      }, err=>{
        console.log(err);
      })

    this.paramService.getFonctionsList()
      .subscribe(data=>{
        this.fonctions = data;
      }, err=>{
        console.log(err);
      })
  }

  onSaveAgent(data) {
    let url = API_BASE_URL+"/saveAgent";
    this.paramService.postResource(url,data)
      .subscribe(data=>{
        this.router.navigateByUrl("/agents")
      },err=>{
        console.log(err);
      })
  }
}
