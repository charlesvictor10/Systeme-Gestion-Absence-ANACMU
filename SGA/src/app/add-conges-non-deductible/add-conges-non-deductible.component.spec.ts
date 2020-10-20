import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCongesNonDeductibleComponent } from './add-conges-non-deductible.component';

describe('AddCongesNonDeductibleComponent', () => {
  let component: AddCongesNonDeductibleComponent;
  let fixture: ComponentFixture<AddCongesNonDeductibleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCongesNonDeductibleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCongesNonDeductibleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
