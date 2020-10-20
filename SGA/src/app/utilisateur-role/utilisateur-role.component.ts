import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Utilisateur} from '../../Model/utilisateur';
import {Role} from '../../Model/role';
import {AdminService} from '../services/admin.service';
import {ParamService} from '../services/param.service';
import {Router} from '@angular/router';
import {API_BASE_ADMIN_URL} from '../constants/constants';

@Component({
  selector: 'app-utilisateur-role',
  templateUrl: './utilisateur-role.component.html',
  styleUrls: ['./utilisateur-role.component.css']
})
export class UtilisateurRoleComponent implements OnInit {
  utilisateurs:Observable<Utilisateur[]>;
  roles:Observable<Role[]>;
  roleUser:Object;

  constructor(private adminService:AdminService, private paramService:ParamService, private  router:Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.adminService.getUtilisateurList()
      .subscribe(data=>{
        this.utilisateurs = data;
      },err=>{
        console.log(err)
      });

    this.adminService.getRoleList()
      .subscribe(data=>{
        this.roles = data;
      },err=>{
        console.log(err)
      });
  }

  onSaveUserRole(data) {
    let url = API_BASE_ADMIN_URL+"/addRoleToUser";
    this.adminService.postResource(url,data)
      .subscribe(data=>{

        this.roleUser = data;
      },err=>{
        console.log(err);
      })
  }
}
