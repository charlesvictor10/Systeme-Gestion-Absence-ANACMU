import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonnellesComponent } from './personnelles.component';

describe('PersonnellesComponent', () => {
  let component: PersonnellesComponent;
  let fixture: ComponentFixture<PersonnellesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PersonnellesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonnellesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
