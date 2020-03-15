import { async, ComponentFixture, TestBed } from "@angular/core/testing";

import { LargePitComponent } from "./large-pit.component";

describe("LargePitComponent", () => {
  let component: LargePitComponent;
  let fixture: ComponentFixture<LargePitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [LargePitComponent]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LargePitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
