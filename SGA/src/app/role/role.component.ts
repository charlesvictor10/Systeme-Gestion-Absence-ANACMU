import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {AdminService} from '../services/admin.service';
import {Router} from '@angular/router';

import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs';
import {Observable} from 'rxjs';
import {Role} from '../../Model/role';
import {API_BASE_ADMIN_URL} from '../constants/constants';

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {
  roles:Observable<Role[]>;
  dataTable: any;

  constructor(private adminService:AdminService, private router:Router, private chRef: ChangeDetectorRef) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.adminService.getRoleList()
      .subscribe(data=>{
        this.roles = data;
        this.chRef.detectChanges();
        const table:any = $('table');
        this.dataTable = table.DataTable();
      },err=>{
        console.log(err);
      })
  }

  delete(id: number) {
    let url = API_BASE_ADMIN_URL+"/role/"+id;
    let conf = confirm("Etes vous sur?");
    if(conf) {
      this.adminService.deleteResource(url)
        .subscribe(data=>{
          this.router.navigateByUrl("/role");
        },err=>{
          console.log(err);
        })
    }
  }
}
