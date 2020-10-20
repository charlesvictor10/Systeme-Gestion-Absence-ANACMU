import {ChangeDetectorRef, Component, OnInit} from '@angular/core';

import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs';
import {Observable} from 'rxjs';
import {Utilisateur} from '../../Model/utilisateur';
import {AdminService} from '../services/admin.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-utilisateur',
  templateUrl: './utilisateur.component.html',
  styleUrls: ['./utilisateur.component.css']
})
export class UtilisateurComponent implements OnInit {
  utilisateurs:Observable<Utilisateur[]>;
  dataTable: any;

  constructor(private adminService:AdminService, private router:Router, private chRef: ChangeDetectorRef) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.adminService.getUtilisateurList()
      .subscribe(data=>{
        this.utilisateurs = data;
        this.chRef.detectChanges();
        const table:any = $('table');
        this.dataTable = table.DataTable();
      },err=>{
        console.log(err);
      })
  }

  edit(id: number) {
    this.router.navigateByUrl("editUtilisateur/"+id);
  }

  delete(id: any) {

  }
}
