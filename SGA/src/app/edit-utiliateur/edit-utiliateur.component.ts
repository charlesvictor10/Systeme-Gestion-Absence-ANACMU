import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AdminService} from '../services/admin.service';
import {Utilisateur} from '../../Model/utilisateur';
import {API_BASE_ADMIN_URL} from '../constants/constants';

@Component({
  selector: 'app-edit-utiliateur',
  templateUrl: './edit-utiliateur.component.html',
  styleUrls: ['./edit-utiliateur.component.css']
})
export class EditUtiliateurComponent implements OnInit {
  utilisateur:Object;
  idUtilisateur:number;

  constructor(public adminService:AdminService, private router:Router, private activatedRoute:ActivatedRoute) {
    this.idUtilisateur = activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.adminService.getUtilisateur(this.idUtilisateur)
      .subscribe(data=>{
        this.utilisateur = data;
      },err=>{
        console.log(err);
      })
  }

  updateUtilisateur(utilisateur:Utilisateur) {
    let url = API_BASE_ADMIN_URL+"/utilisateurs/"+utilisateur.id;
    this.adminService.updateResource(url,utilisateur)
      .subscribe(data=>{
        this.router.navigateByUrl("/utilisateurs");
      },err=>{
        console.log(err);
      })
  }
}
