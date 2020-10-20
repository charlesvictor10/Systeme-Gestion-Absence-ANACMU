import {Component, OnInit} from '@angular/core';
import {ParamService} from '../services/param.service';
import {Router} from '@angular/router';
import {API_BASE_URL} from '../constants/constants';
import {Observable} from 'rxjs';
import {Annee} from '../../Model/annee';

@Component({
  selector: 'app-add-jour-ferie',
  templateUrl: './add-jour-ferie.component.html',
  styleUrls: ['./add-jour-ferie.component.css']
})
export class AddJourFerieComponent implements OnInit {
  annees: Observable<Annee[]>;

  constructor(private paramService:ParamService, private router:Router) {
  }

  ngOnInit() {
    this.relaodData();
  }

  relaodData(){
    this.paramService.getAnneesList()
      .subscribe(data=>{
        this.annees = data;
      },err=>{
        console.log(err);
      })
  }

  onSaveJourFerie(data) {
    let url = API_BASE_URL+"/saveJourFerie";
    this.paramService.postResource(url,data)
      .subscribe(data=>{
        this.router.navigateByUrl("/jourFeries")
      },err=>{
        console.log(err);
      })
  }
}
