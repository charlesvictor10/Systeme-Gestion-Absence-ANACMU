import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Agent} from '../../Model/agent';
import {ParamService} from '../services/param.service';
import {Router} from '@angular/router';

import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs';
import {AuthenticationService} from '../services/authentication.service';

@Component({
  selector: 'app-agents',
  templateUrl: './agents.component.html',
  styleUrls: ['./agents.component.css']
})
export class AgentsComponent implements OnInit {
  agents: Observable<Agent[]>;
  dataTable: any;

  constructor(private authService:AuthenticationService,private paramService:ParamService, private router:Router, private chRef: ChangeDetectorRef) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.paramService.getAgentsList()
      .subscribe(data=>{
        this.agents = data;
        this.chRef.detectChanges();
        const table:any = $('table');
        this.dataTable = table.DataTable();
      });
  }

  newAgent() {
    this.router.navigateByUrl("/addAgent");
  }

  detail(id:number) {
    this.router.navigateByUrl("/detailAgent/"+id);
  }
}
