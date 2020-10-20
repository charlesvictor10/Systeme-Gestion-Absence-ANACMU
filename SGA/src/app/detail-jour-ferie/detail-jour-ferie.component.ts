import { Component, OnInit } from '@angular/core';
import {ParamService} from '../services/param.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-detail-jour-ferie',
  templateUrl: './detail-jour-ferie.component.html',
  styleUrls: ['./detail-jour-ferie.component.css']
})
export class DetailJourFerieComponent implements OnInit {
  jourFerie:Object;
  idJourFerie:number;

  constructor(public activatedRoute:ActivatedRoute, public paramService:ParamService, private router:Router) {
    this.idJourFerie=activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.paramService.getJourFerie(this.idJourFerie)
      .subscribe(data=>{
        this.jourFerie = data;
      }, err=>{
        console.log(err);
      })
  }

  detailJourFerie(id:number) {
    return this.router.navigateByUrl("/editJourFerie/"+id);
  }

  close() {
    this.router.navigateByUrl("/jourFeries");
  }
}
