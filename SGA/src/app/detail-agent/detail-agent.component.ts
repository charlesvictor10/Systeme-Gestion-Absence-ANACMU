import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ParamService} from '../services/param.service';
import {AuthenticationService} from '../services/authentication.service';
import {EtatService} from '../services/etat.service';
import {Agent} from '../../Model/agent';

@Component({
  selector: 'app-detail-agent',
  templateUrl: './detail-agent.component.html',
  styleUrls: ['./detail-agent.component.css']
})
export class DetailAgentComponent implements OnInit {
  agent:Object;
  agt:Agent;
  idAgent:number;
  soldeConge:Object;

  constructor(public activatedRoute:ActivatedRoute, private paramService: ParamService, private etatService:EtatService, private authService:AuthenticationService, private router:Router) {
    this.idAgent=activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.paramService.getAgent(this.idAgent)
      .subscribe(data=>{
        this.agent = data;
      }, err=>{
        console.log(err)
      });

    this.authService.currentUser()
      .subscribe(data=>{
        this.agt = data['agent'].id;
        this.etatService.getSoldeCongeParAgent(this.agt)
          .subscribe(data=>{
            this.soldeConge = data;
          })
      },err=>{
        console.log(err);
      })
  }

  editAgent(id){
    this.router.navigateByUrl("/editAgent/"+id);
  }

  close() {
    this.router.navigateByUrl("/agents");
  }
}
