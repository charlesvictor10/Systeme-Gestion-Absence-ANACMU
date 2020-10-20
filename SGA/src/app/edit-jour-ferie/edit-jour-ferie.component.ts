import { Component, OnInit } from '@angular/core';
import {ParamService} from '../services/param.service';
import {ActivatedRoute, Router} from '@angular/router';
import {API_BASE_URL} from '../constants/constants';
import {JourFerie} from '../../Model/jourFerie';
import {Observable} from 'rxjs';
import {Annee} from '../../Model/annee';

@Component({
  selector: 'app-edit-jour-ferie',
  templateUrl: './edit-jour-ferie.component.html',
  styleUrls: ['./edit-jour-ferie.component.css']
})
export class EditJourFerieComponent implements OnInit {
  jourFeries:object;
  idJourDerie:number;
  annees: Observable<Annee[]>;
  currentAnnee:any;

  constructor(public paramService:ParamService, private router:Router, private activatedRoute:ActivatedRoute) {
    this.idJourDerie = activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.relaodData();
  }

  relaodData(){
    this.paramService.getJourFerie(this.idJourDerie)
      .subscribe(data=>{
        this.jourFeries = data;
        this.currentAnnee = this.jourFeries['annee']['annee'];
      }, err=>{
        console.log(err);
      });

    this.paramService.getAnneesList()
      .subscribe(data=>{
        this.annees = data;
      },err=>{
        console.log(err);
      })
  }

  updateJourFerie(jourFerie:JourFerie) {
    let url = API_BASE_URL+"/jourFeries/"+jourFerie.id;
    this.paramService.updateResource(url,jourFerie)
      .subscribe(data=>{
        this.router.navigateByUrl("/jourFeries");
      },err=>{
        console.log(err);
      })
  }
}
