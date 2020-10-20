import {Agent} from './agent';

export class Utilisateur {
  id:number;
  email:string;
  email_verified:string;
  password:string;
  newPassword:string;
  confirmPassword:string;
  actived:boolean;
  raisonBlocage:string;
  agent:Agent;
}
