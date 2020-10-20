import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UtilisateurRoleComponent } from './utilisateur-role.component';

describe('UtilisateurRoleComponent', () => {
  let component: UtilisateurRoleComponent;
  let fixture: ComponentFixture<UtilisateurRoleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UtilisateurRoleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UtilisateurRoleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
