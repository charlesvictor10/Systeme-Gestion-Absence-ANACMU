import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailDemandeTraiteesComponent } from './detail-demande-traitees.component';

describe('DetailDemandeTraiteesComponent', () => {
  let component: DetailDemandeTraiteesComponent;
  let fixture: ComponentFixture<DetailDemandeTraiteesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailDemandeTraiteesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailDemandeTraiteesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
