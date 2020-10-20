import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Agent} from '../../Model/agent';
import {DemandeService} from '../services/demande.service';
import {ParamService} from '../services/param.service';
import {Router} from '@angular/router';
import {API_BASE_DEMANDE_URL} from '../constants/constants';
import {Absence} from '../../Model/absence';
import {SoldeConges} from '../../Model/soldeConges';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-absence-deductible',
  templateUrl: './absence-deductible.component.html',
  styleUrls: ['./absence-deductible.component.css']
})
export class AbsenceDeductibleComponent implements OnInit {
  agents: Observable<Agent[]>;
  agent:Agent;
  selectdOption: Object;
  selectedDateDebut:any;
  selectedDateFin:any;
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

  ajaxCalculDateNbj(){
    let dateDebut = this.selectedDateDebut;
    dateDebut = this.datePipe.transform(dateDebut);
    let dateFin = this.selectedDateFin;
    dateFin = this.datePipe.transform(dateFin);
    this.demandeService.calculDateNbj(dateDebut,dateFin)
      .subscribe(data=>{
        this.absence = data[0];
        this.sc = data[1];
      },err=>{
        console.log(err);
      })
  }

  onSaveAbsenceDeductible(data) {
    let url = API_BASE_DEMANDE_URL+"/enregistrerPermAbsDeducConge";
    this.demandeService.postResource(url,data)
      .subscribe(data=>{
        this.router.navigateByUrl("/demandeParService")
      },err=>{
        this.errorMessage = err['error'].message;
      })
  }
}
