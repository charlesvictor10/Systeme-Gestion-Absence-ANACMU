import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ParamService} from '../services/param.service';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {CongesNonDeductible} from '../../Model/congesNonDeductible';

import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs';
import {AuthenticationService} from '../services/authentication.service';

@Component({
  selector: 'app-conges-non-deductible',
  templateUrl: './conges-non-deductible.component.html',
  styleUrls: ['./conges-non-deductible.component.css']
})
export class CongesNonDeductibleComponent implements OnInit {
  congesNonDeductibles: Observable<CongesNonDeductible[]>;
  dataTable: any;

  constructor(private authService:AuthenticationService,private paramService:ParamService, private router:Router, private chRef: ChangeDetectorRef) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.paramService.getCongesNonDeductiblesList()
      .subscribe(data=>{
        this.congesNonDeductibles = data;
        this.chRef.detectChanges();
        const table:any = $('table');
        this.dataTable = table.DataTable();
      });
  }

  detail(id:number) {
    this.router.navigateByUrl('/detailCongesNonDeductible/'+id);
  }

  newCongesNonDeductible() {
    this.router.navigateByUrl('/addCongesNonDeductible');
  }
}
