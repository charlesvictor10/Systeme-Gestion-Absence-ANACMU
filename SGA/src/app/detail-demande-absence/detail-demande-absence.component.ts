import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DemandeService} from '../services/demande.service';

@Component({
  selector: 'app-detail-demande-absence',
  templateUrl: './detail-demande-absence.component.html',
  styleUrls: ['./detail-demande-absence.component.css']
})
export class DetailDemandeAbsenceComponent implements OnInit {
  demande:Object;
  idDemandePermissionAbsence:number;

  constructor(public activatedRoute:ActivatedRoute, private demandeService: DemandeService, private router:Router) {
    this.idDemandePermissionAbsence=activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.demandeService.getDemandePermission(this.idDemandePermissionAbsence)
      .subscribe(data=>{
        this.demande = data;
      },err=>{
        console.log(err);
      })
  }

  editDemandeAbsence(id:number) {
    this.router.navigateByUrl("/editDemandeAbsence/"+id);
  }

  close() {
    this.router.navigateByUrl("/demandeAbsences");
  }
}
