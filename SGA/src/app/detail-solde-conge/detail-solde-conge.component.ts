import { Component, OnInit } from '@angular/core';
import {EtatService} from '../services/etat.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-detail-solde-conge',
  templateUrl: './detail-solde-conge.component.html',
  styleUrls: ['./detail-solde-conge.component.css']
})
export class DetailSoldeCongeComponent implements OnInit {
  soldeConge:Object;
  idSoldeConge:number;

  constructor(private activatedRoute:ActivatedRoute, private etatService:EtatService) {
    this.idSoldeConge = activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.etatService.getSoldeConge(this.idSoldeConge)
      .subscribe(data=>{
        this.soldeConge = data;
      },err=>{
        console.log(err);
      })
  }
}
