import { Component, OnInit } from "@angular/core";
import { GameService } from "src/app/services/game.service";
import { GameState } from "src/app/models/GameState";
import { Pit } from "src/app/models/Pit";
import { Player } from 'src/app/models/Player';

@Component({
  selector: "app-board",
  templateUrl: "./board.component.html",
  styleUrls: ["./board.component.css"]
})
export class BoardComponent implements OnInit {
  gameState: GameState;
  loading: boolean = true;

  constructor(private gameService: GameService) {
    this.gameService.getNewGame().subscribe(recievedGameState => {
      this.gameState = recievedGameState;
      this.loading = false;
    });
  }

  ngOnInit(): void {}

  createPit(pebbles: number, position: number, player: Player): Pit {
    return new Pit(pebbles, position, player.id, !player.move);
  }

  onPebblesMove(pit: Pit) {
    this.gameService
      .movePebbles(this.gameState.mancala, pit.playerId, pit.position)
      .subscribe(recievedGameState => {
        this.gameState = recievedGameState;
      });
  }

  reload() {
    location.reload();
  }

}
