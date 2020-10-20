import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAbsenceEffectiveComponent } from './add-absence-effective.component';

describe('AddAbsenceEffectiveComponent', () => {
  let component: AddAbsenceEffectiveComponent;
  let fixture: ComponentFixture<AddAbsenceEffectiveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddAbsenceEffectiveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAbsenceEffectiveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
