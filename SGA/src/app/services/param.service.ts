import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {API_BASE_URL} from '../constants/constants';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ParamService {

  constructor(public http:HttpClient) { }

  getAgentsList(): Observable<any> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_URL+"/agents",{headers:headers});
  }

  getCongesNonDeductiblesList(): Observable<any> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_URL+"/congesNonDeductibles",{headers:headers});
  }

  getAnneesList(): Observable<any> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_URL+"/annees",{headers:headers});
  }

  getCivilitesList(): Observable<any> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_URL+"/civilites",{headers:headers});
  }

  getEntitesList(): Observable<any> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_URL+"/entites",{headers:headers});
  }

  getStatutsList(): Observable<any> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_URL+"/statuts",{headers:headers});
  }

  getServicesList(): Observable<any> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_URL+"/services",{headers:headers});
  }

  getFonctionsList(): Observable<any> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_URL+"/fonctions",{headers:headers});
  }

  getJourFeriesList(): Observable<any> {
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_URL+"/jourFeries",{headers:headers});
  }

  getCongesNonDeductible(id:number): Observable<Object>{
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_URL+"/congesNonDeductibles/"+id,{headers:headers});
  }

  getJourFerie(id:number): Observable<Object>{
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_URL+"/jourFeries/"+id,{headers:headers});
  }

  getAnnee(id:number): Observable<Object>{
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_URL+"/annees/"+id,{headers:headers});
  }

  getAgent(id:number): Observable<Object>{
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.get(API_BASE_URL+"/agents/"+id,{headers:headers});
  }

  postResource(url,data){
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.post(url,data,{headers:headers});
  }

  updateResource(url,data){
    let headers = new HttpHeaders({'authorization':'Bearer '+localStorage.getItem("token")});
    return this.http.put(url,data,{headers:headers});
  }
}
