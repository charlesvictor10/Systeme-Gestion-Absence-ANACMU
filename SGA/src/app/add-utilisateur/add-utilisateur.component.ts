import { Component, OnInit } from '@angular/core';
import {AdminService} from '../services/admin.service';
import {API_BASE_ADMIN_URL} from '../constants/constants';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Agent} from '../../Model/agent';
import {ParamService} from '../services/param.service';
import {Role} from '../../Model/role';

@Component({
  selector: 'app-add-utilisateur',
  templateUrl: './add-utilisateur.component.html',
  styleUrls: ['./add-utilisateur.component.css']
})
export class AddUtilisateurComponent implements OnInit {
  utilisateur:Object;
  agents:Observable<Agent[]>;
  roles:Observable<Role[]>;

  constructor(private adminService:AdminService, private paramService:ParamService, private  router:Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.paramService.getAgentsList()
      .subscribe(data=>{
        this.agents = data;
      },err=>{
        console.log(err)
      });

    this.adminService.getRoleList()
      .subscribe(data=>{
        this.roles = data;
      },err=>{
        console.log(err);
      })
  }

  onSaveUser(data) {
    let url = API_BASE_ADMIN_URL+"/signup";
    this.adminService.postResource(url,data)
      .subscribe(data=>{
        this.utilisateur = data;
        this.router.navigateByUrl("/addUserRole");
      },err=>{
        console.log(err);
      })
  }
}
