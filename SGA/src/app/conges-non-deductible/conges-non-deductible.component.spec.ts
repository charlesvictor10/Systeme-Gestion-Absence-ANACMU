import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CongesNonDeductibleComponent } from './conges-non-deductible.component';

describe('CongesNonDeductibleComponent', () => {
  let component: CongesNonDeductibleComponent;
  let fixture: ComponentFixture<CongesNonDeductibleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CongesNonDeductibleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CongesNonDeductibleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
