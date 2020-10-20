import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Absence} from '../../Model/absence';
import {AuthenticationService} from '../services/authentication.service';
import {DemandeService} from '../services/demande.service';
import {Router} from '@angular/router';

import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs';

@Component({
  selector: 'app-validation-conge',
  templateUrl: './validation-conge.component.html',
  styleUrls: ['./validation-conge.component.css']
})
export class ValidationCongeComponent implements OnInit {
  demandesRH:Observable<Absence[]>;
  dataTable: any;

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
}
