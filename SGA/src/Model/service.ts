import {Observable} from 'rxjs';
import {Agent} from './agent';

export class Service {
  id:number;
  code:string;
  lib:string;
  description:string;
  niveau:string;
  agent:Observable<Agent[]>;
  service_rattachement:Observable<Service[]>;
}
