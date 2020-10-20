import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JourferiesComponent } from './jourferies.component';

describe('JourferiesComponent', () => {
  let component: JourferiesComponent;
  let fixture: ComponentFixture<JourferiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JourferiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JourferiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
