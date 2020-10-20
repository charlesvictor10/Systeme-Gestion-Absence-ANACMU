import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailDemandeAbsenceComponent } from './detail-demande-absence.component';

describe('DetailDemandeAbsenceComponent', () => {
  let component: DetailDemandeAbsenceComponent;
  let fixture: ComponentFixture<DetailDemandeAbsenceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailDemandeAbsenceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailDemandeAbsenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
