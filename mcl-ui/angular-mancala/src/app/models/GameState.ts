import { Mancala } from "./Mancala";

export class GameState {
  id: number;
  mancala: Mancala;
  gameEnded: boolean;
  winner: number;
}
