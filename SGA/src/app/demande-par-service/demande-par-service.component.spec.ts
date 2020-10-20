import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandeParServiceComponent } from './demande-par-service.component';

describe('DemandeParServiceComponent', () => {
  let component: DemandeParServiceComponent;
  let fixture: ComponentFixture<DemandeParServiceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DemandeParServiceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DemandeParServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
