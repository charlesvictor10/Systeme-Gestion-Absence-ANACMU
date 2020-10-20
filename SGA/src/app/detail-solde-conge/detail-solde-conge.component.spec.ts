import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailSoldeCongeComponent } from './detail-solde-conge.component';

describe('DetailSoldeCongeComponent', () => {
  let component: DetailSoldeCongeComponent;
  let fixture: ComponentFixture<DetailSoldeCongeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailSoldeCongeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailSoldeCongeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
