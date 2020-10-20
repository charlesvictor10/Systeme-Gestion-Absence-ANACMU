import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlanningCongeComponent } from './planning-conge.component';

describe('PlanningCongeComponent', () => {
  let component: PlanningCongeComponent;
  let fixture: ComponentFixture<PlanningCongeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlanningCongeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlanningCongeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
