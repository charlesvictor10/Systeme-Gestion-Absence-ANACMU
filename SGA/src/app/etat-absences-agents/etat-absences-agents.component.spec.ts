import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EtatAbsencesAgentsComponent } from './etat-absences-agents.component';

describe('EtatAbsencesAgentsComponent', () => {
  let component: EtatAbsencesAgentsComponent;
  let fixture: ComponentFixture<EtatAbsencesAgentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EtatAbsencesAgentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EtatAbsencesAgentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
