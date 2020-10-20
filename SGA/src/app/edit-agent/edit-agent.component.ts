import { Component, OnInit } from '@angular/core';
import {ParamService} from '../services/param.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Agent} from '../../Model/agent';
import {API_BASE_URL} from '../constants/constants';
import {Observable} from 'rxjs';
import {Civilite} from '../../Model/civilite';
import {Statut} from '../../Model/statut';
import {Fonction} from '../../Model/fonction';
import {Service} from '../../Model/service';
import {Entite} from '../../Model/entite';

@Component({
  selector: 'app-edit-agent',
  templateUrl: './edit-agent.component.html',
  styleUrls: ['./edit-agent.component.css']
})
export class EditAgentComponent implements OnInit {
  agent:Object;
  idAgent:number;
  civilites: Observable<Civilite[]>;
  currentCivilite:object;
  statuts: Observable<Statut[]>;
  currentStatut:object;
  superviseurs: Observable<Agent[]>;
  currentSuperviseur:object;
  fonctions: Observable<Fonction[]>;
  currentFonction:object;
  services: Observable<Service[]>;
  currentService:object;
  entites: Observable<Entite[]>;
  currentEntite:object;

  constructor(private paramService:ParamService,private router:Router,private activatedRoute:ActivatedRoute) {
    this.idAgent = activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.reloadData();
    this.paramService.getAgent(this.idAgent)
      .subscribe(data=>{
        this.agent = data;
        this.currentCivilite = this.agent['civilite'].lib;
        this.currentStatut = this.agent['statut'].lib;
        this.currentSuperviseur = this.agent[''];
        this.currentFonction = this.agent['fonction'].lib;
        this.currentService = this.agent['service'].lib;
        this.currentEntite = this.agent['entite'].lib;
      }, err=>{
        console.log(err);
      })
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

  updateAgent(agent:Agent){
    let url = API_BASE_URL+"/agents/"+agent.id;
    this.paramService.updateResource(url,agent)
      .subscribe(data=>{
        this.router.navigateByUrl("/agents")
      },err=>{
        console.log(err);
      })
  }
}
