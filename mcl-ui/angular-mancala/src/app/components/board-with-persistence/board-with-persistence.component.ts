import { Component, OnInit } from "@angular/core";
import { PersistenceGameService } from "src/app/services/persistence-game.service";
import { GameState } from "src/app/models/GameState";
import { Pit } from "src/app/models/Pit";
import { Player } from "src/app/models/Player";

@Component({
  selector: "app-board-with-persistance",
  templateUrl: "./board-with-persistance.component.html",
  styleUrls: ["./board-with-persistance.component.css"]
})
export class BoardWithPersistenceComponent implements OnInit {
  gameState: GameState;
  loading: boolean = true;
  id: number = this.getPreviousGameId();

  constructor(private gameService: PersistenceGameService) {
    this.gameService.getGame(this.id).subscribe(recievedGameState => {
      this.gameState = recievedGameState;
      this.loading = false;
      this.id = recievedGameState.id;
      this.saveGameId(this.id.toString());
    });
  }

  ngOnInit(): void {}

  createPit(pebbles: number, position: number, player: Player): Pit {
    return new Pit(pebbles, position, player.id, !player.move);
  }

  onPebblesMove(pit: Pit) {
    this.gameService
      .movePebbles(this.id, this.gameState.mancala, pit.playerId, pit.position)
      .subscribe(recievedGameState => {
        this.gameState = recievedGameState;
      });
  }

  reload() {
    this.saveGameId("-1");
    location.reload();
  }

  getPreviousGameId(): number {
    let previousId = +localStorage.getItem("gameId");
    return previousId ? previousId : -1;
  }

  saveGameId(id: string) {
    localStorage.setItem("gameId", id);
  }

  gameReset() {
    this.saveGameId("-1");
    this.reload();
  }
}
