import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddValidationDemandeComponent } from './add-validation-demande.component';

describe('AddValidationDemandeComponent', () => {
  let component: AddValidationDemandeComponent;
  let fixture: ComponentFixture<AddValidationDemandeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddValidationDemandeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddValidationDemandeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
