import { Component, ViewChild } from '@angular/core';
import { FullCalendarComponent } from '@fullcalendar/angular';
import { EventInput } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGrigPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import {AuthenticationService} from '../services/authentication.service';
import {DemandeService} from '../services/demande.service';
import {Service} from '../../Model/service';

@Component({
  selector: 'app-planning-conge',
  templateUrl: './planning-conge.component.html',
  styleUrls: ['./planning-conge.component.css']
})
export class PlanningCongeComponent {
  service:Service;
  calendarEvents: EventInput[];

  @ViewChild('calendar') calendarComponent: FullCalendarComponent;

  constructor(private authService:AuthenticationService, private demandeService:DemandeService) {
    this.eventData();
  }

  calendarVisible = true;
  calendarPlugins = [dayGridPlugin, timeGrigPlugin, interactionPlugin];
  calendarWeekends = true;

  eventData() {
    this.authService.currentUser()
      .subscribe(data=>{
        this.service = data['agent']['service'].id;
        this.demandeService.getDemandePermissionParService(this.service)
          .subscribe(data=>{
           for(var i = 0; i < data.length; i++){
             this.calendarEvents = [
               { title: data[i]['absence']['motif'], start: data[i]['absence']['dateDebutAbsence'], end: data[i]['absence']['dateFinAbsence'] },
               { title: data[i]['absence']['congesNonDeductible'], start: data[i]['absence']['dateDebutAbsence'], end: data[i]['absence']['dateFinAbsence'] }
             ];
           }
          })
      });
  }
}
