import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {EtatService} from '../services/etat.service';

import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs';
import {SoldeConges} from '../../Model/soldeConges';
import {AuthenticationService} from '../services/authentication.service';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-solde-conge',
  templateUrl: './solde-conge.component.html',
  styleUrls: ['./solde-conge.component.css']
})
export class SoldeCongeComponent implements OnInit {
  soldeConges:Observable<SoldeConges[]>;
  dataTable: any;
  url: string;
  errorMessage: string;
  succesMessage: string;

  constructor(public http:HttpClient, private authService:AuthenticationService, private etatService:EtatService, private router:Router, private chRef: ChangeDetectorRef) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.etatService.getSoldeCongesAgentList()
      .subscribe(data=>{
        this.soldeConges = data;
        this.chRef.detectChanges();
        const table:any = $('table');
        this.dataTable = table.DataTable();
      });
  }

  exportExcel() {
    this.etatService.getExcel()
      .subscribe((response) => {
        this.succesMessage = 'Exportation du solde des congés effectuée avec succés.'
      },err=>{
        this.errorMessage = 'Un erreur c\'est produit lors de l\'exportation du fichier.'
      })
  }

  calculSolde() {
    this.etatService.calculSoldeConge()
      .subscribe(res=>{
        console.log(res);
        this.succesMessage = 'Solde des congés calculée avec succés.'
      },err=>{
        this.errorMessage = 'Un erreur c\'est produit lors du calcul des congés';
      })
  }

  info(id:number) {
    this.router.navigateByUrl("detailSoldeConge/"+id);
  }

  modifier(id:number) {
    this.router.navigateByUrl("editSoldeConge/"+id);
  }

  ajouter(id:number) {
    this.router.navigateByUrl("addAbsenceEffective/"+id);
  }
}
