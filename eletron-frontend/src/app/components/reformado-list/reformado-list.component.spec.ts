import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReformadoListComponent } from './reformado-list.component';

describe('ReformadoListComponent', () => {
  let component: ReformadoListComponent;
  let fixture: ComponentFixture<ReformadoListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReformadoListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReformadoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
