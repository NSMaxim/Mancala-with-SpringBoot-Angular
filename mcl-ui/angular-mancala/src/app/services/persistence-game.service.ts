import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { GameState } from "../models/GameState";
import { PlayerMove } from "../models/PlayerMove";
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PersistenceGameService {
  constructor(private http: HttpClient) {}

  getGame(id: number): Observable<GameState> {
    return this.http.get<GameState>(environment.apiUrl + '/with-persistence/continue?id=' + id);
  }

  movePebbles(
    id: number,
    playerToMove: number,
    smallPitPosition: number
  ): Observable<GameState> {
    return this.http.post<GameState>(
      environment.apiUrl + '/with-persistence/make-move?id=' + id,
      new PlayerMove(id, playerToMove, smallPitPosition)
    );
  }
}
