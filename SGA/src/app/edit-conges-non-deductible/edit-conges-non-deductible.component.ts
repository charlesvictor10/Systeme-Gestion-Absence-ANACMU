import { Component, OnInit } from '@angular/core';
import {ParamService} from '../services/param.service';
import {ActivatedRoute, Router} from '@angular/router';
import {API_BASE_URL} from '../constants/constants';
import {CongesNonDeductible} from '../../Model/congesNonDeductible';

@Component({
  selector: 'app-edit-conges-non-deductible',
  templateUrl: './edit-conges-non-deductible.component.html',
  styleUrls: ['./edit-conges-non-deductible.component.css']
})
export class EditCongesNonDeductibleComponent implements OnInit {
  congesNonDeductible:Object;
  idCongesNonDeductible:number;

  constructor(private paramService:ParamService,private router:Router,private activatedRoute:ActivatedRoute) {
    this.idCongesNonDeductible = activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.paramService.getCongesNonDeductible(this.idCongesNonDeductible)
      .subscribe(data=>{
        this.congesNonDeductible = data;
      }, err=>{
        console.log(err);
      })
  }

  updateCongesNonDeductible(congesNonDeductible:CongesNonDeductible) {
    let url = API_BASE_URL+"/congesNonDeductibles/"+congesNonDeductible.id;
    this.paramService.updateResource(url,congesNonDeductible)
      .subscribe(data=>{
        this.congesNonDeductible = data;
        this.router.navigateByUrl("/congeNonDeductible")
      },err=>{
        console.log(err);
      })
  }
}
