import { Component, OnInit } from '@angular/core';
import {EtatService} from '../services/etat.service';
import {ActivatedRoute, Router} from '@angular/router';
import {SoldeConges} from '../../Model/soldeConges';
import {API_BASE_ETAT_ABSENCE_URL} from '../constants/constants';

@Component({
  selector: 'app-edit-solde-conge',
  templateUrl: './edit-solde-conge.component.html',
  styleUrls: ['./edit-solde-conge.component.css']
})
export class EditSoldeCongeComponent implements OnInit {
  soldeConge:Object;
  idSoldeConge:number;

  constructor(private activatedRoute:ActivatedRoute, private etatService:EtatService, private router:Router) {
    this.idSoldeConge=activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.etatService.getSoldeConge(this.idSoldeConge)
      .subscribe(data=>{
        this.soldeConge = data;
      },err=>{
        console.log(err);
      })
  }

  updateSoldeConge(data){
    let url = API_BASE_ETAT_ABSENCE_URL+"/saveSoldeConge";
    this.etatService.postResource(url,data)
      .subscribe(data=>{
        this.router.navigateByUrl("/soldeConge");
      },err=>{
        console.log(err);
      })
  }

  close() {
    this.router.navigateByUrl("/soldeConge");
  }
}
