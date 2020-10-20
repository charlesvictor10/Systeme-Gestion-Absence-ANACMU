import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Agent} from '../../Model/agent';
import {CongesNonDeductible} from '../../Model/congesNonDeductible';
import {DemandeService} from '../services/demande.service';
import {ParamService} from '../services/param.service';
import {Router} from '@angular/router';
import {API_BASE_DEMANDE_URL} from '../constants/constants';
import {Absence} from '../../Model/absence';
import {SoldeConges} from '../../Model/soldeConges';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-absence-non-deductible',
  templateUrl: './absence-non-deductible.component.html',
  styleUrls: ['./absence-non-deductible.component.css']
})
export class AbsenceNonDeductibleComponent implements OnInit {
  agents: Observable<Agent[]>;
  agent:Agent;
  congesNonDeductible:CongesNonDeductible;
  congesNonDeductibles:Observable<CongesNonDeductible[]>;
  selectdOption: Object;
  selectCnd:Object;
  sc:SoldeConges;
  abs:Absence;
  absence:Absence;
  errorMessage:string;

  constructor(private demandeService:DemandeService, private paramService:ParamService, private router:Router, private datePipe:DatePipe) {
    this.sc = new SoldeConges(0.0,0.0,0.0,0.0,0.0,0.0);
    this.abs = new Absence(null,null,0.0,0.0,0.0,null,"");
    this.absence = new Absence(null,null,0.0,0.0,0.0,null,"");
  }

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

    this.paramService.getCongesNonDeductiblesList()
      .subscribe(data=>{
        this.congesNonDeductibles = data;
      },err=>{
        console.log(err)
      });
  }

  ajaxAgent() {
    this.agent = this.selectdOption['id'];
    this.demandeService.getSoldeAgent(this.agent)
      .subscribe(data=>{
        this.sc = data;
      },err=>{
        console.log(err);
      })
  }

  ajaxCongesNonDeductible() {
    this.congesNonDeductible = this.selectCnd['id'];
    this.demandeService.getNbJourNonDeductible(this.congesNonDeductible)
      .subscribe(data=>{
        this.abs = data;
      },err=>{
        console.log(err);
      })
  }

  ajaxCalculDate(event:any){
    let dateDebut = event.target.value;
    dateDebut = this.datePipe.transform(dateDebut);
    this.demandeService.calculDate(dateDebut,this.abs.nbJourNonDeductible)
      .subscribe(data=>{
        this.absence = data;
        this.absence.dateFinAbsence = this.datePipe.transform(this.absence.dateFinAbsence,"yyyy-MM-dd");
      },err=>{
        console.log(err);
      })
  }

  onSaveAbsenceNonDeductible(data) {
    let url = API_BASE_DEMANDE_URL+"/enregistrerPermAbsNonDeducConge";
    this.demandeService.postResource(url,data)
      .subscribe(data=>{
        this.router.navigateByUrl("/demandeParService")
      },err=>{
        this.errorMessage = err['error'].message;
      })
  }
}
