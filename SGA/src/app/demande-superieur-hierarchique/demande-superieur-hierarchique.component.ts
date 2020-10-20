import {ChangeDetectorRef, Component, OnInit} from '@angular/core';

import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs';
import {Observable} from 'rxjs';
import {Absence} from '../../Model/absence';
import {AuthenticationService} from '../services/authentication.service';
import {DemandeService} from '../services/demande.service';
import {Router} from '@angular/router';
import {Agent} from '../../Model/agent';

@Component({
  selector: 'app-demande-superieur-hierarchique',
  templateUrl: './demande-superieur-hierarchique.component.html',
  styleUrls: ['./demande-superieur-hierarchique.component.css']
})
export class DemandeSuperieurHierarchiqueComponent implements OnInit {
  demandesSH:Observable<Absence[]>;
  sh:Agent;
  dataTable: any;

  constructor(private authService:AuthenticationService, private demandeService:DemandeService, private router:Router, private chRef: ChangeDetectorRef) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.authService.currentUser()
      .subscribe(data=>{
        this.sh = data['agent'].id;
        this.demandeService.getDemAbsAValiderParSH(this.sh)
          .subscribe(data=> {
            this.demandesSH = data;
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

  detail(id:number) {
    this.router.navigateByUrl("/detailDemandeAbsence/"+id);
  }
}
