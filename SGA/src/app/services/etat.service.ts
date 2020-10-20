import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {API_BASE_ETAT_ABSENCE_URL} from '../constants/constants';
import {Agent} from '../../Model/agent';
import 'rxjs-compat/add/operator/map';

@Injectable({
  providedIn: 'root'
})
export class EtatService {

  constructor(public http:HttpClient) { }

  getEtatAbsenceAgentList(): Observable<any> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_ETAT_ABSENCE_URL+"/listeEtatAbsenceAgent",{headers:headers})
  }

  getSoldeCongesAgentList(): Observable<any> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_ETAT_ABSENCE_URL+"/listeSoldeCongesAgent",{headers:headers});
  }

  getSoldeConge(id:number): Observable<Object> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_ETAT_ABSENCE_URL+"/soldeConges/"+id,{headers:headers});
  }

  getSoldeCongeParAgent(agent:Agent): Observable<any> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_ETAT_ABSENCE_URL+"/soldeCongesParAgent/?agent="+agent, {headers:headers});
  }

  postResource(url,data){
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.post(url,data,{headers:headers});
  }

  calculDateNbj(dateDebut:Date,dateFin:Date): Observable<any>{
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_ETAT_ABSENCE_URL+"/calculDateNbj/?dateDebut="+dateDebut+"&dateFin="+dateFin,{headers:headers});
  }

  calculSoldeConge(): Observable<any>{
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_ETAT_ABSENCE_URL+"/calculSolde",{headers:headers});
  }

  getExcel(){
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token"),'Content-Type':'application/vnd.ms-excel'});
    return this.http.get(API_BASE_ETAT_ABSENCE_URL+"/exportExcel",{headers:headers, responseType:'blob'});
  }
}
