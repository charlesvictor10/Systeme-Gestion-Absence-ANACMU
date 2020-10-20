import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandeSuperieurHierarchiqueComponent } from './demande-superieur-hierarchique.component';

describe('DemandeSuperieurHierarchiqueComponent', () => {
  let component: DemandeSuperieurHierarchiqueComponent;
  let fixture: ComponentFixture<DemandeSuperieurHierarchiqueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DemandeSuperieurHierarchiqueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DemandeSuperieurHierarchiqueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
