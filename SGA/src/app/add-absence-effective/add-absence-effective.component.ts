import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Agent} from '../../Model/agent';
import {ParamService} from '../services/param.service';
import {ActivatedRoute, Router} from '@angular/router';
import {API_BASE_ETAT_ABSENCE_URL} from '../constants/constants';
import {EtatService} from '../services/etat.service';
import {DemandeService} from '../services/demande.service';
import {DatePipe} from '@angular/common';
import {SoldeConges} from '../../Model/soldeConges';
import {Absence} from '../../Model/absence';

@Component({
  selector: 'app-add-absence-effective',
  templateUrl: './add-absence-effective.component.html',
  styleUrls: ['./add-absence-effective.component.css']
})
export class AddAbsenceEffectiveComponent implements OnInit {
  agents: Observable<Agent[]>;
  agent:Agent;
  selectedDateDebut:any;
  selectedDateFin:any;
  sc:SoldeConges;
  absence:Absence;
  idSoldeConge:number;

  constructor(private activatedRoute:ActivatedRoute, private etatService:EtatService, private router:Router, private datePipe:DatePipe) {
    this.idSoldeConge = activatedRoute.snapshot.params['id'];
    this.sc = new SoldeConges(0.0,0.0,0.0,0.0,0.0,0.0);
    this.absence = new Absence(null,null,0.0,0.0,0.0,null,"");
  }

  ngOnInit() {
    this.etatService.getSoldeConge(this.idSoldeConge)
      .subscribe(data=>{
        this.etatService.getSoldeCongeParAgent(data['agent'].id)
          .subscribe(data=>{
            this.sc = data;
          },err=>{
            console.log(err);
          })
      },err=>{
        console.log(err);
      })
  }

  ajaxCalculDateNbj(){
    let dateDebut = this.selectedDateDebut;
    dateDebut = this.datePipe.transform(dateDebut);
    let dateFin = this.selectedDateFin;
    dateFin = this.datePipe.transform(dateFin);
    this.etatService.calculDateNbj(dateDebut,dateFin)
      .subscribe(data=>{
        this.absence = data[0];
        console.log(this.absence.nbJourAbsence);
        this.sc = data[1];
      },err=>{
        console.log(err);
      })
  }

  saveAbsenceEffective(data) {
    let url = API_BASE_ETAT_ABSENCE_URL+"/enregistrerAbsence";
    this.etatService.postResource(url,data)
      .subscribe(data=>{
        this.router.navigateByUrl("/demandeParService");
      },err=>{
        console.log(err);
      })
  }
}
