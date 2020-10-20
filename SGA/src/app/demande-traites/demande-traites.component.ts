import {ChangeDetectorRef, Component, OnInit} from '@angular/core';

import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs';
import {Observable} from 'rxjs';
import {Absence} from '../../Model/absence';
import {DemandeService} from '../services/demande.service';
import {Router} from '@angular/router';
import {AuthenticationService} from '../services/authentication.service';
import {Service} from '../../Model/service';

@Component({
  selector: 'app-demande-traites',
  templateUrl: './demande-traites.component.html',
  styleUrls: ['./demande-traites.component.css']
})
export class DemandeTraitesComponent implements OnInit {
  demandes:Observable<Absence[]>;
  service:Service;
  dataTable: any;

  constructor(private authService:AuthenticationService,private demandeService:DemandeService,private chRef:ChangeDetectorRef,private router:Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData(){
    this.authService.currentUser()
      .subscribe(data=>{
        this.service = data['agent']['service'].id;
        this.demandeService.getDemandePermissionParService(this.service)
          .subscribe(data=>{
            this.demandeService.getDemAbsTraitees()
              .subscribe(data=>{
                this.demandes = data;
                this.chRef.detectChanges();
                const table:any = $('table');
                this.dataTable = table.DataTable();
              },err=>{
                console.log(err);
              })
          },err=>{
            console.log(err);
          })
      },err=>{
        console.log(err);
      })
  }

  detail(id:number) {
    this.router.navigateByUrl("/detailDemandeTraitee/"+id);
  }
}
