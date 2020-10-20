import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditSoldeCongeComponent } from './edit-solde-conge.component';

describe('EditSoldeCongeComponent', () => {
  let component: EditSoldeCongeComponent;
  let fixture: ComponentFixture<EditSoldeCongeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditSoldeCongeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditSoldeCongeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
