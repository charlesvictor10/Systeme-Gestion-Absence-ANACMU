import { Component, OnInit } from '@angular/core';
import {ParamService} from '../services/param.service';
import {Router} from '@angular/router';
import {API_BASE_URL} from '../constants/constants';
import {CongesNonDeductible} from '../../Model/congesNonDeductible';

@Component({
  selector: 'app-add-conges-non-deductible',
  templateUrl: './add-conges-non-deductible.component.html',
  styleUrls: ['./add-conges-non-deductible.component.css']
})
export class AddCongesNonDeductibleComponent implements OnInit {

  congesNonDeductible:CongesNonDeductible = new CongesNonDeductible();

  constructor(public paramService:ParamService, public router:Router) { }

  ngOnInit() {
  }

  newEmployee(): void {
    this.congesNonDeductible = new CongesNonDeductible();
  }

  onSaveCongesNonDeductible(data) {
    let url = API_BASE_URL+"/savecongesNonDeductible";
    this.paramService.postResource(url,data)
      .subscribe(data=>{
        this.congesNonDeductible = new CongesNonDeductible();
        this.router.navigateByUrl("/congeNonDeductible")
      },err=>{
        console.log(err);
      })
  }
}
