import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { GameState } from "../models/GameState";
import { PlayerMove } from "../models/PlayerMove";
import { Mancala } from "../models/Mancala";

@Injectable({
  providedIn: "root"
})
export class GameService {
  constructor(private http: HttpClient) {}

  getNewGame(): Observable<GameState> {
    return this.http.get<GameState>('/new-game');
  }

  movePebbles(
    mancala: Mancala,
    playerToMove: number,
    smallPitPosition: number
  ): Observable<GameState> {
    return this.http.post<GameState>(
      '/make-move',
      new PlayerMove(mancala, playerToMove, smallPitPosition)
    );
  }
}
