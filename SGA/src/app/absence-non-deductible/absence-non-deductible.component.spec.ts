import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AbsenceNonDeductibleComponent } from './absence-non-deductible.component';

describe('AbsenceNonDeductibleComponent', () => {
  let component: AbsenceNonDeductibleComponent;
  let fixture: ComponentFixture<AbsenceNonDeductibleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AbsenceNonDeductibleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AbsenceNonDeductibleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
