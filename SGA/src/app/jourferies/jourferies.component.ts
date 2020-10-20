import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ParamService} from '../services/param.service';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {JourFerie} from '../../Model/jourFerie';

import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs';
import {AuthenticationService} from '../services/authentication.service';

@Component({
  selector: 'app-jourferies',
  templateUrl: './jourferies.component.html',
  styleUrls: ['./jourferies.component.css']
})
export class JourferiesComponent implements OnInit {
  jourFeries: Observable<JourFerie[]>;
  dataTable: any;

  constructor(private authService:AuthenticationService, private paramService:ParamService, private router:Router, private chRef: ChangeDetectorRef) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData(){
    this.paramService.getJourFeriesList()
      .subscribe(data=>{
        this.jourFeries = data;
        this.chRef.detectChanges();
        const table:any = $('table');
        this.dataTable = table.DataTable();
      });
  }

  onDetailJourFerie(id:number) {
    this.router.navigateByUrl('detailJourFerie/'+id);
  }

  newJourFerie() {
    this.router.navigateByUrl('/addJourFerie');
  }
}
