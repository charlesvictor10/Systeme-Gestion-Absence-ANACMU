import {ChangeDetectorRef, Component, OnInit} from '@angular/core';

import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs';
import {Observable} from 'rxjs';
import {Absence} from '../../Model/absence';
import {AuthenticationService} from '../services/authentication.service';
import {DemandeService} from '../services/demande.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-demande-rh',
  templateUrl: './demande-rh.component.html',
  styleUrls: ['./demande-rh.component.css']
})
export class DemandeRhComponent implements OnInit {
  demandesRH:Observable<Absence[]>;
  id:number;
  idAbs:number;
  dataTable: any;
  errorMessage: string;
  succesMessage: string;

  constructor(private authService:AuthenticationService, private demandeService:DemandeService, private router:Router, private chRef: ChangeDetectorRef) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.authService.currentUser()
      .subscribe(data=>{
        this.demandeService.getDemAbsAValiderParRH()
          .subscribe(data=> {
            this.demandesRH = data;
            this.chRef.detectChanges();
            const table: any = $('table');
            this.dataTable = table.DataTable();
          },err=>{
            console.log(err);
          })
      },err=>{
        console.log(err);
      })
  }

  goValid(id:number) {
    this.router.navigateByUrl("/addValidationDemande/"+id);
  }

  printPDF(id:number) {
    this.demandeService.getDemandePermission(id)
      .subscribe(data=>{
        this.idAbs = data['absence'].id;
        this.demandeService.exportPdf(this.idAbs)
          .subscribe((response) => {
            this.succesMessage = 'Succés du téléchargement de la demande.'
          })
      },err=>{
        this.errorMessage = 'Erreur lors du téléchargement de la demande.'
      })
  }
}
