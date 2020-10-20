import { Component, OnInit } from '@angular/core';
import {AdminService} from '../services/admin.service';
import {ParamService} from '../services/param.service';
import {Router} from '@angular/router';
import {API_BASE_ADMIN_URL} from '../constants/constants';

@Component({
  selector: 'app-add-role',
  templateUrl: './add-role.component.html',
  styleUrls: ['./add-role.component.css']
})
export class AddRoleComponent implements OnInit {
  role:Object;

  constructor(private adminService:AdminService, private  router:Router) { }

  ngOnInit() {
  }

  onSaveRole(data) {
    let url = API_BASE_ADMIN_URL+"saveRole";
    this.adminService.postResource(url,data)
      .subscribe(data=>{
        this.role = data;
        this.router.navigateByUrl("/role");
      },err=>{
        console.log(err);
      })
  }
}
