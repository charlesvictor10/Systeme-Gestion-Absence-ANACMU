import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditDemandeAbsenceComponent } from './edit-demande-absence.component';

describe('EditDemandeAbsenceComponent', () => {
  let component: EditDemandeAbsenceComponent;
  let fixture: ComponentFixture<EditDemandeAbsenceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditDemandeAbsenceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditDemandeAbsenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
