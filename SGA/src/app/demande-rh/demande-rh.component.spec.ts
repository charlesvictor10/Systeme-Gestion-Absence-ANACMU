import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandeRhComponent } from './demande-rh.component';

describe('DemandeRhComponent', () => {
  let component: DemandeRhComponent;
  let fixture: ComponentFixture<DemandeRhComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DemandeRhComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DemandeRhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
