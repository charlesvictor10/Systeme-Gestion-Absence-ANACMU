import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap';

import { LoginComponent } from './login/login.component';
import { JourferiesComponent } from './jourferies/jourferies.component';
import { EditJourFerieComponent } from './edit-jour-ferie/edit-jour-ferie.component';
import { AddJourFerieComponent } from './add-jour-ferie/add-jour-ferie.component';
import { DetailJourFerieComponent } from './detail-jour-ferie/detail-jour-ferie.component';
import { CongesNonDeductibleComponent } from './conges-non-deductible/conges-non-deductible.component';
import { AddCongesNonDeductibleComponent } from './add-conges-non-deductible/add-conges-non-deductible.component';
import { EditCongesNonDeductibleComponent } from './edit-conges-non-deductible/edit-conges-non-deductible.component';
import { AgentsComponent } from './agents/agents.component';
import { AddAgentComponent } from './add-agent/add-agent.component';
import { HeaderComponent } from './header/header.component';
import { LeftSideComponent } from './left-side/left-side.component';
import { FooterComponent } from './footer/footer.component';
import { PersonnellesComponent } from './personnelles/personnelles.component';
import { HistoriquesComponent } from './historiques/historiques.component';
import { DetailCongeNonDeductibleComponent } from './detail-conge-non-deductible/detail-conge-non-deductible.component';
import { DetailAgentComponent } from './detail-agent/detail-agent.component';
import { EditAgentComponent } from './edit-agent/edit-agent.component';
import { DemandeTraitesComponent } from './demande-traites/demande-traites.component';
import { DetailDemandeAbsenceComponent } from './detail-demande-absence/detail-demande-absence.component';
import { EditDemandeAbsenceComponent } from './edit-demande-absence/edit-demande-absence.component';
import { DetailDemandeTraiteesComponent } from './detail-demande-traitees/detail-demande-traitees.component';
import { AbsenceDeductibleComponent } from './absence-deductible/absence-deductible.component';
import { AbsenceNonDeductibleComponent } from './absence-non-deductible/absence-non-deductible.component';
import { SoldeCongeComponent } from './solde-conge/solde-conge.component';
import { AddValidationDemandeComponent } from './add-validation-demande/add-validation-demande.component';
import { EditPasswordComponent } from './edit-password/edit-password.component';
import { GuideUtilisateurComponent } from './guide-utilisateur/guide-utilisateur.component';
import { PlanningCongeComponent } from './planning-conge/planning-conge.component';
import { EtatAbsencesAgentsComponent } from './etat-absences-agents/etat-absences-agents.component';
import { DetailSoldeCongeComponent } from './detail-solde-conge/detail-solde-conge.component';
import { EditSoldeCongeComponent } from './edit-solde-conge/edit-solde-conge.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { AddUtilisateurComponent } from './add-utilisateur/add-utilisateur.component';
import { UtilisateurComponent } from './utilisateur/utilisateur.component';
import { UtilisateurRoleComponent } from './utilisateur-role/utilisateur-role.component';
import { RoleComponent } from './role/role.component';
import { AddRoleComponent } from './add-role/add-role.component';
import { EditUtiliateurComponent } from './edit-utiliateur/edit-utiliateur.component';
import { AddSoldeCongesComponent } from './add-solde-conges/add-solde-conges.component';
import { DemandeParServiceComponent } from './demande-par-service/demande-par-service.component';
import { DemandeSuperieurHierarchiqueComponent } from './demande-superieur-hierarchique/demande-superieur-hierarchique.component';
import { DemandeRhComponent } from './demande-rh/demande-rh.component';
import { AddAbsenceEffectiveComponent } from './add-absence-effective/add-absence-effective.component';
import {DatePipe} from '@angular/common';
import { ValidationCongeComponent } from './validation-conge/validation-conge.component';
import {FullCalendarModule} from '@fullcalendar/angular';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    JourferiesComponent,
    EditJourFerieComponent,
    AddJourFerieComponent,
    DetailJourFerieComponent,
    CongesNonDeductibleComponent,
    AddCongesNonDeductibleComponent,
    EditCongesNonDeductibleComponent,
    AgentsComponent,
    AddAgentComponent,
    HeaderComponent,
    LeftSideComponent,
    FooterComponent,
    PersonnellesComponent,
    HistoriquesComponent,
    DetailCongeNonDeductibleComponent,
    DetailAgentComponent,
    EditAgentComponent,
    DemandeTraitesComponent,
    DetailDemandeAbsenceComponent,
    EditDemandeAbsenceComponent,
    DetailDemandeTraiteesComponent,
    AbsenceDeductibleComponent,
    AbsenceNonDeductibleComponent,
    SoldeCongeComponent,
    AddValidationDemandeComponent,
    EditPasswordComponent,
    GuideUtilisateurComponent,
    PlanningCongeComponent,
    EtatAbsencesAgentsComponent,
    DetailSoldeCongeComponent,
    EditSoldeCongeComponent,
    ForgotPasswordComponent,
    ResetPasswordComponent,
    AddUtilisateurComponent,
    UtilisateurComponent,
    UtilisateurRoleComponent,
    RoleComponent,
    AddRoleComponent,
    EditUtiliateurComponent,
    AddSoldeCongesComponent,
    DemandeParServiceComponent,
    DemandeSuperieurHierarchiqueComponent,
    DemandeRhComponent,
    AddAbsenceEffectiveComponent,
    ValidationCongeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    FullCalendarModule,
    ModalModule.forRoot(),
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent],
  entryComponents: [ForgotPasswordComponent]
})
export class AppModule { }
