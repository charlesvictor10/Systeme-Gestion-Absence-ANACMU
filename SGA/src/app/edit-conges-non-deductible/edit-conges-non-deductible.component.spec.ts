import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCongesNonDeductibleComponent } from './edit-conges-non-deductible.component';

describe('EditCongesNonDeductibleComponent', () => {
  let component: EditCongesNonDeductibleComponent;
  let fixture: ComponentFixture<EditCongesNonDeductibleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditCongesNonDeductibleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCongesNonDeductibleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
