import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DemandeService} from '../services/demande.service';
import {Observable} from 'rxjs';
import {ValidationDemande} from '../../Model/validationDemande';
import {DemandePermissionAbsence} from '../../Model/demandePermissionAbsence';

@Component({
  selector: 'app-detail-demande-traitees',
  templateUrl: './detail-demande-traitees.component.html',
  styleUrls: ['./detail-demande-traitees.component.css']
})
export class DetailDemandeTraiteesComponent implements OnInit {
  demande:Object;
  validationDemandes:Observable<ValidationDemande[]>;
  demandePermissionAbsence:DemandePermissionAbsence;
  idDemandePermissionAbsence:number;

  constructor(public activatedRoute:ActivatedRoute, private demandeService: DemandeService, private router:Router) {
    this.idDemandePermissionAbsence=activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.demandeService.getDemandePermission(this.idDemandePermissionAbsence)
      .subscribe(data=>{
        this.demande = data;
        this.demandePermissionAbsence = this.demande['id'];
        this.demandeService.getValidationParDemandePermissionAbsence(this.demandePermissionAbsence)
          .subscribe(data=>{
            this.validationDemandes = data;
          }, err=>{
            console.log(err);
          })
      },err=>{
        console.log(err)
      });
  }

  close() {
    this.router.navigateByUrl("/demandeTraitees");
  }
}
