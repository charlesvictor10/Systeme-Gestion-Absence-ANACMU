import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditUtiliateurComponent } from './edit-utiliateur.component';

describe('EditUtiliateurComponent', () => {
  let component: EditUtiliateurComponent;
  let fixture: ComponentFixture<EditUtiliateurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditUtiliateurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditUtiliateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
