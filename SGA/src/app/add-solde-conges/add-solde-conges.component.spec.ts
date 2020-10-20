import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSoldeCongesComponent } from './add-solde-conges.component';

describe('AddSoldeCongesComponent', () => {
  let component: AddSoldeCongesComponent;
  let fixture: ComponentFixture<AddSoldeCongesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddSoldeCongesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSoldeCongesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
