import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {JourferiesComponent} from './jourferies/jourferies.component';
import {AddJourFerieComponent} from './add-jour-ferie/add-jour-ferie.component';
import {EditJourFerieComponent} from './edit-jour-ferie/edit-jour-ferie.component';
import {DetailJourFerieComponent} from './detail-jour-ferie/detail-jour-ferie.component';
import {CongesNonDeductibleComponent} from './conges-non-deductible/conges-non-deductible.component';
import {AddCongesNonDeductibleComponent} from './add-conges-non-deductible/add-conges-non-deductible.component';
import {EditCongesNonDeductibleComponent} from './edit-conges-non-deductible/edit-conges-non-deductible.component';
import {AgentsComponent} from './agents/agents.component';
import {AddAgentComponent} from './add-agent/add-agent.component';
import {PersonnellesComponent} from './personnelles/personnelles.component';
import {HistoriquesComponent} from './historiques/historiques.component';
import {DetailCongeNonDeductibleComponent} from './detail-conge-non-deductible/detail-conge-non-deductible.component';
import {DetailAgentComponent} from './detail-agent/detail-agent.component';
import {EditAgentComponent} from './edit-agent/edit-agent.component';
import {DemandeTraitesComponent} from './demande-traites/demande-traites.component';
import {DetailDemandeAbsenceComponent} from './detail-demande-absence/detail-demande-absence.component';
import {EditDemandeAbsenceComponent} from './edit-demande-absence/edit-demande-absence.component';
import {DetailDemandeTraiteesComponent} from './detail-demande-traitees/detail-demande-traitees.component';
import {AbsenceDeductibleComponent} from './absence-deductible/absence-deductible.component';
import {AbsenceNonDeductibleComponent} from './absence-non-deductible/absence-non-deductible.component';
import {SoldeCongeComponent} from './solde-conge/solde-conge.component';
import {AddValidationDemandeComponent} from './add-validation-demande/add-validation-demande.component';
import {EditPasswordComponent} from './edit-password/edit-password.component';
import {GuideUtilisateurComponent} from './guide-utilisateur/guide-utilisateur.component';
import {PlanningCongeComponent} from './planning-conge/planning-conge.component';
import {EtatAbsencesAgentsComponent} from './etat-absences-agents/etat-absences-agents.component';
import {DetailSoldeCongeComponent} from './detail-solde-conge/detail-solde-conge.component';
import {EditSoldeCongeComponent} from './edit-solde-conge/edit-solde-conge.component';
import {ResetPasswordComponent} from './reset-password/reset-password.component';
import {AddUtilisateurComponent} from './add-utilisateur/add-utilisateur.component';
import {UtilisateurComponent} from './utilisateur/utilisateur.component';
import {UtilisateurRoleComponent} from './utilisateur-role/utilisateur-role.component';
import {AddRoleComponent} from './add-role/add-role.component';
import {RoleComponent} from './role/role.component';
import {EditUtiliateurComponent} from './edit-utiliateur/edit-utiliateur.component';
import {DemandeParServiceComponent} from './demande-par-service/demande-par-service.component';
import {DemandeSuperieurHierarchiqueComponent} from './demande-superieur-hierarchique/demande-superieur-hierarchique.component';
import {DemandeRhComponent} from './demande-rh/demande-rh.component';
import {AddSoldeCongesComponent} from './add-solde-conges/add-solde-conges.component';
import {AddAbsenceEffectiveComponent} from './add-absence-effective/add-absence-effective.component';
import {ValidationCongeComponent} from './validation-conge/validation-conge.component';

const routes: Routes = [
  {path:"addUtilisateur", component:AddUtilisateurComponent},
  {path:"addUserRole", component:UtilisateurRoleComponent},
  {path:"utilisateur", component:UtilisateurComponent},
  {path:"editUtilisateur/:id", component:EditUtiliateurComponent},

  {path:"addRole", component:AddRoleComponent},
  {path:"role", component:RoleComponent},

  {path:"login", component:LoginComponent},
  {path:"resetPassword", component:ResetPasswordComponent},
  {path:"editPassword", component:EditPasswordComponent},
  {path:"", component:PlanningCongeComponent},

  {path:"jourFeries", component:JourferiesComponent},
  {path:"addJourFerie", component:AddJourFerieComponent},
  {path:"detailJourFerie/:id", component:DetailJourFerieComponent},
  {path:"editJourFerie/:id", component:EditJourFerieComponent},

  {path:"congeNonDeductible", component:CongesNonDeductibleComponent},
  {path:"addCongesNonDeductible", component:AddCongesNonDeductibleComponent},
  {path:"detailCongesNonDeductible/:id", component:DetailCongeNonDeductibleComponent},
  {path:"editCongesNonDeductible/:id", component:EditCongesNonDeductibleComponent},

  {path:"agents", component:AgentsComponent},
  {path:"addAgent", component:AddAgentComponent},
  {path:"detailAgent/:id", component:DetailAgentComponent},
  {path:"editAgent/:id", component:EditAgentComponent},

  {path:"personnelles", component:PersonnellesComponent},
  {path:"historiques", component:HistoriquesComponent},

  {path:"absenceDeductible", component:AbsenceDeductibleComponent},
  {path:"absenceNonDeductible", component:AbsenceNonDeductibleComponent},
  {path:"detailDemandeAbsence/:id", component:DetailDemandeAbsenceComponent},
  {path:"editDemandeAbsence/:id", component:EditDemandeAbsenceComponent},

  {path:"addAbsenceEffective/:id", component:AddAbsenceEffectiveComponent},

  {path:"demandeParService", component:DemandeParServiceComponent},
  {path:"demandeSH", component:DemandeSuperieurHierarchiqueComponent},
  {path:"demandeRH", component:DemandeRhComponent},
  {path:"demandeVD", component:ValidationCongeComponent},
  {path:"demandeTraitees", component:DemandeTraitesComponent},
  {path:"detailDemandeTraitee/:id", component:DetailDemandeTraiteesComponent},

  {path:"addValidationDemande/:id", component:AddValidationDemandeComponent},

  {path:"addSoldeConge", component:AddSoldeCongesComponent},
  {path:"soldeConge", component:SoldeCongeComponent},
  {path:"detailSoldeConge/:id", component:DetailSoldeCongeComponent},
  {path:"editSoldeConge/:id", component:EditSoldeCongeComponent},

  {path:"etatAbsencesAgents", component:EtatAbsencesAgentsComponent},

  {path:'guideUtilisateur', component:GuideUtilisateurComponent},

  {path:'**', redirectTo:''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
