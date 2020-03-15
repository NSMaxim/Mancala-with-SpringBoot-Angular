import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { GameState } from "../models/GameState";
import { PlayerMove } from "../models/PlayerMove";
import { Mancala } from "../models/Mancala";

@Injectable({
  providedIn: 'root'
})
export class PersistenceGameService {
  constructor(private http: HttpClient) {}

  getGame(id: number): Observable<GameState> {
    return this.http.get<GameState>('/with-persistence/continue?id=' + id);
  }

  movePebbles(
    id: number,
    mancala: Mancala,
    playerToMove: number,
    smallPitPosition: number
  ): Observable<GameState> {
    return this.http.post<GameState>(
      '/with-persistence/make-move?id=' + id,
      new PlayerMove(mancala, playerToMove, smallPitPosition, id)
    );
  }
}
