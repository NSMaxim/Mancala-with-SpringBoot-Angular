import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { GameState } from "../models/GameState";
import { PlayerMove } from "../models/PlayerMove";
import { Mancala } from "../models/Mancala";
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: "root"
})
export class GameService {
  constructor(private http: HttpClient) {}

  getNewGame(): Observable<GameState> {
    return this.http.get<GameState>(environment.apiUrl + '/new-game');
  }

  movePebbles(
    mancala: Mancala,
    playerToMove: number,
    smallPitPosition: number
  ): Observable<GameState> {
    return this.http.post<GameState>(
      environment.apiUrl + '/make-move',
      new PlayerMove(-1, playerToMove, smallPitPosition, mancala)
    );
  }
}
