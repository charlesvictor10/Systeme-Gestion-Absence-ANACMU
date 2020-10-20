import { Component, OnInit } from '@angular/core';
import {DemandeService} from '../services/demande.service';
import {Observable} from 'rxjs';
import {DemandePermissionAbsence} from '../../Model/demandePermissionAbsence';
import {NiveauValidation} from '../../Model/niveauValidation';
import {API_BASE_DEMANDE_URL} from '../constants/constants';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '../services/authentication.service';
import {ParamService} from '../services/param.service';
import {ValidationDemande} from '../../Model/validationDemande';

@Component({
  selector: 'app-add-validation-demande',
  templateUrl: './add-validation-demande.component.html',
  styleUrls: ['./add-validation-demande.component.css']
})
export class AddValidationDemandeComponent implements OnInit {
  demandePermission: Object;
  demandePermissionAbsence:DemandePermissionAbsence;
  niveauValidations: Observable<NiveauValidation[]>;
  validationDemandes:Observable<ValidationDemande[]>;
  idvalidateur:number;
  validateur:Object;
  idDemandePermission:number;

  constructor(private demandeService:DemandeService, private paramService:ParamService, private authService:AuthenticationService, private router:Router, private activatedRoute:ActivatedRoute) {
    this.idDemandePermission = activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.reloadData();
  }

  reloadData(){
    this.authService.currentUser()
      .subscribe(data=>{
        this.idvalidateur = data['agent'].id;
        this.paramService.getAgent(this.idvalidateur)
          .subscribe(data=>{
            this.validateur = data;
          },err=>{
            console.log(err);
          })
      },err=>{
        console.log(err)
      });

    this.demandeService.getDemandePermission(this.idDemandePermission)
      .subscribe(data=>{
        this.demandePermission = data;
        this.demandePermissionAbsence = this.demandePermission['id'];
        this.demandeService.getValidationParDemandePermissionAbsence(this.demandePermissionAbsence)
          .subscribe(data=>{
            this.validationDemandes = data;
            console.log(data);
          }, err=>{
            console.log(err);
          })
      },err=>{
        console.log(err)
      });

    this.demandeService.getNiveauValidationList()
      .subscribe(data=>{
        this.niveauValidations = data;
      },err=>{
        console.log(err)
      });
  }

  onSaveValidation(data) {
    let url = API_BASE_DEMANDE_URL+"/accepter";
    this.demandeService.postResource(url,data)
      .subscribe(data=>{
        this.router.navigateByUrl("/demandeAbsences");
      },err=>{
        console.log(err);
      })
  }

  /*refuser($event: Event) {
    this.demandeService.refuser()
      .subscribe(data=>{

      },err=>{

      })
  }

  accepter($event: Event) {
    this.demandeService.accepter()
      .subscribe(data=>{

      },err=>{

      })
  }*/
}
