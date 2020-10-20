import { Component, OnInit } from '@angular/core';
import {ParamService} from '../services/param.service';
import {Router} from '@angular/router';
import {Utilisateur} from '../../Model/utilisateur';
import {API_BASE_URL} from '../constants/constants';

@Component({
  selector: 'app-edit-password',
  templateUrl: './edit-password.component.html',
  styleUrls: ['./edit-password.component.css']
})
export class EditPasswordComponent implements OnInit {
  utilisateur:Utilisateur = new Utilisateur();
  errorMessage:String;
  succesMessage:String;

  constructor(private paramService:ParamService, private router:Router) {
  }

  ngOnInit() {
  }

  updateUtilisateur(data) {
    let url = API_BASE_URL+"/changePassword";
    this.paramService.postResource(url,data)
      .subscribe(data=>{
        this.succesMessage = "Mot de passe modifié avec succés. Veuillez vous reconnecter.";
        this.router.navigateByUrl("/editPassword");
      },err=>{
        this.errorMessage = "Les deux mots de passe sont différents.";
      })
  }
}
