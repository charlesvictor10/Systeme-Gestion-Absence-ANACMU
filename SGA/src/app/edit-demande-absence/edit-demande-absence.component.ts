import { Component, OnInit } from '@angular/core';
import {ParamService} from '../services/param.service';
import {ActivatedRoute, Router} from '@angular/router';
import {DemandeService} from '../services/demande.service';
import {API_BASE_DEMANDE_URL} from '../constants/constants';
import {Observable} from 'rxjs';
import {Agent} from '../../Model/agent';
import {CongesNonDeductible} from '../../Model/congesNonDeductible';
import {TypeAbsence} from '../../Model/typeAbsence';
import {DemandePermissionAbsence} from '../../Model/demandePermissionAbsence';

@Component({
  selector: 'app-edit-demande-absence',
  templateUrl: './edit-demande-absence.component.html',
  styleUrls: ['./edit-demande-absence.component.css']
})
export class EditDemandeAbsenceComponent implements OnInit {
  agents: Observable<Agent[]>;
  currentAgent:Object;
  congesNonDeductibles: Observable<CongesNonDeductible[]>;
  currentCongesNonDeductible:Object;
  demande:Object;
  typeAbsences:Observable<TypeAbsence[]>;
  currentType:object;
  idDemandePermissionAbsence:number;

  constructor(private demandeService:DemandeService,private paramService:ParamService,private router:Router,private activatedRoute:ActivatedRoute) {
    this.idDemandePermissionAbsence = activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
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

    this.demandeService.getTypeAbsenceList()
      .subscribe(data=>{
        this.typeAbsences = data;
      },err=>{
        console.log(err)
      });

    this.demandeService.getDemandePermission(this.idDemandePermissionAbsence)
      .subscribe(data=>{
        this.demande = data;
        this.currentAgent = this.demande['absence']['agent'].prenom;
        this.currentCongesNonDeductible = this.demande['absence']['congesNonDeductible'].lib;
        this.currentType = this.demande['absence']['typeAbsence'].lib;
      },err=>{
        console.log(err);
      })
  }

  updateAbsence(demande:DemandePermissionAbsence) {
    let url = API_BASE_DEMANDE_URL+"/permissions/"+demande.id;
    this.demandeService.updateResource(url,demande)
      .subscribe(data=>{
        this.demande = data;
        console.log(data);
        this.router.navigateByUrl("/demandeAbsences");
      },err=>{
        console.log(err);
      })
  }
}
