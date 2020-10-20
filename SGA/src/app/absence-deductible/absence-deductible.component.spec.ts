import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AbsenceDeductibleComponent } from './absence-deductible.component';

describe('AbsenceDeductibleComponent', () => {
  let component: AbsenceDeductibleComponent;
  let fixture: ComponentFixture<AbsenceDeductibleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AbsenceDeductibleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AbsenceDeductibleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
