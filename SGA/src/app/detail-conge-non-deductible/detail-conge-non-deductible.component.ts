import {Component, OnInit} from '@angular/core';
import {ParamService} from '../services/param.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-detail-conge-non-deductible',
  templateUrl: './detail-conge-non-deductible.component.html',
  styleUrls: ['./detail-conge-non-deductible.component.css']
})
export class DetailCongeNonDeductibleComponent implements OnInit {
  congesNonDeductible:Object;
  idCongesNonDeductible:number;

  constructor(public activatedRoute:ActivatedRoute, private paramService: ParamService, private router:Router) {
    this.idCongesNonDeductible=activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.paramService.getCongesNonDeductible(this.idCongesNonDeductible)
      .subscribe(data=>{
        this.congesNonDeductible = data;
      }, err=>{
        console.log(err);
      })
  }

  editCongesNonDeductible(id) {
    this.router.navigateByUrl("/editCongesNonDeductible/"+id);
  }

  close() {
    this.router.navigateByUrl("/congeNonDeductible");
  }
}
