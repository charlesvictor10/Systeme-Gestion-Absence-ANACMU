import {ChangeDetectorRef, Component, OnInit} from '@angular/core';

import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs';
import {DemandeService} from '../services/demande.service';
import {AuthenticationService} from '../services/authentication.service';
import {Agent} from '../../Model/agent';

@Component({
  selector: 'app-historiques',
  templateUrl: './historiques.component.html',
  styleUrls: ['./historiques.component.css']
})
export class HistoriquesComponent implements OnInit {
  demandes:Object;
  agent:Agent;
  dataTable: any;

  constructor(private authService:AuthenticationService,private demandeService:DemandeService,private chRef:ChangeDetectorRef) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.authService.currentUser()
      .subscribe(data=>{
        this.agent = data['agent'].id;
        this.demandeService.getDemAbsParAgent(this.agent)
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
  }
}
