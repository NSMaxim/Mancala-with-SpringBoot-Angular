import { Component, OnInit, Input } from "@angular/core";
import { Pit } from "src/app/models/Pit";

@Component({
  selector: "app-large-pit",
  templateUrl: "./large-pit.component.html",
  styleUrls: ["./large-pit.component.css"]
})
export class LargePitComponent implements OnInit {
  @Input() pit: Pit;

  constructor() {}

  ngOnInit(): void {}

  setClasses() {
    let classes = {
      largePit: true,
      player1LargePit: this.pit.playerId === 1,
      player2LargePit: this.pit.playerId !== 1,
      disabled: this.pit.disabled
    };
    return classes;
  }
}
