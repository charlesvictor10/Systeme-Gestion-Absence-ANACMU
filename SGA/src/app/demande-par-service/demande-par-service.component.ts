import {ChangeDetectorRef, Component, OnInit} from '@angular/core';

import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs';
import {Observable} from 'rxjs';
import {Absence} from '../../Model/absence';
import {Service} from '../../Model/service';
import {AuthenticationService} from '../services/authentication.service';
import {DemandeService} from '../services/demande.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-demande-par-service',
  templateUrl: './demande-par-service.component.html',
  styleUrls: ['./demande-par-service.component.css']
})
export class DemandeParServiceComponent implements OnInit {
  demandesParService:Observable<Absence[]>;
  service:Service;
  dataTable: any;

  constructor(private authService:AuthenticationService, private demandeService:DemandeService, private router:Router, private chRef: ChangeDetectorRef) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.authService.currentUser()
      .subscribe(data=>{
        this.service = data['agent']['service'].id;
        this.demandeService.getDemandePermissionParService(this.service)
          .subscribe(data=>{
            this.demandesParService = data;
            this.chRef.detectChanges();
            const table:any = $('table');
            this.dataTable = table.DataTable();
          },err=>{
            console.log(err);
          })
      },err=>{
        console.log(err);
      })
  }

  newAbsenceNonDeductible() {
    this.router.navigateByUrl("/absenceNonDeductible");
  }

  newAbsenceDeductible() {
    this.router.navigateByUrl("/absenceDeductible");
  }
}
