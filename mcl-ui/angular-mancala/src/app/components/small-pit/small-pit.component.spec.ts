import { async, ComponentFixture, TestBed } from "@angular/core/testing";

import { SmallPitComponent } from "./small-pit.component";

describe("SmallPitComponent", () => {
  let component: SmallPitComponent;
  let fixture: ComponentFixture<SmallPitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SmallPitComponent]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SmallPitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
