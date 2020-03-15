import { async, ComponentFixture, TestBed } from "@angular/core/testing";

import { BoardWithPersistenceComponent } from "./board-with-persistence.component";

describe("BoardWithPersistenceComponent", () => {
  let component: BoardWithPersistenceComponent;
  let fixture: ComponentFixture<BoardWithPersistenceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [BoardWithPersistenceComponent]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoardWithPersistenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
