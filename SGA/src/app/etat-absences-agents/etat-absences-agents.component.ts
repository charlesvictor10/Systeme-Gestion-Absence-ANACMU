import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {EtatService} from '../services/etat.service';
import {Observable} from 'rxjs';
import {Absence} from '../../Model/absence';

import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs';

@Component({
  selector: 'app-etat-absences-agents',
  templateUrl: './etat-absences-agents.component.html',
  styleUrls: ['./etat-absences-agents.component.css']
})
export class EtatAbsencesAgentsComponent implements OnInit {
  etatAbsencesAgents: Observable<Absence[]>;
  dataTable: any;

  constructor(private etatService:EtatService, private chRef: ChangeDetectorRef) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData(){
    this.etatService.getEtatAbsenceAgentList()
      .subscribe(data=>{
        this.etatAbsencesAgents = data;
        this.chRef.detectChanges();
        const table:any = $('table');
        this.dataTable = table.DataTable();
      },err=>{
        console.log(err);
      })
  }
}
