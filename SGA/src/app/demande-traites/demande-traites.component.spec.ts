import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandeTraitesComponent } from './demande-traites.component';

describe('DemandeTraitesComponent', () => {
  let component: DemandeTraitesComponent;
  let fixture: ComponentFixture<DemandeTraitesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DemandeTraitesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DemandeTraitesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
