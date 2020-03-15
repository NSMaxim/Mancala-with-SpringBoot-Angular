import { Component, OnInit, Input, Output } from "@angular/core";
import { EventEmitter } from "@angular/core";
import { Pit } from "src/app/models/Pit";

@Component({
  selector: "app-small-pit",
  templateUrl: "./small-pit.component.html",
  styleUrls: ["./small-pit.component.css"]
})
export class SmallPitComponent implements OnInit {
  @Input() pit: Pit;
  @Output() movePebbles: EventEmitter<Pit> = new EventEmitter();

  constructor() {}

  ngOnInit(): void {}

  movePebblesFromPit() {
    if (!this.pit.disabled) {
      this.movePebbles.emit(this.pit);
    }
  }

  setClasses() {
    let classes = {
      smallPit: true,
      player1SmallPit: this.pit.playerId === 1,
      player2SmallPit: this.pit.playerId !== 1,
      disabled: this.pit.disabled
    };
    return classes;
  }
}
