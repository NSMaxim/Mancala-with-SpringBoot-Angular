import { Mancala } from "./Mancala";

export class PlayerMove {
  constructor(
    id: number,
    playerToMove: number,
    smallPitToMoveFrom: number,
    mancala?: Mancala
  ) {
    this.id = id;
    this.playerToMove = playerToMove;
    this.smallPitToMoveFrom = smallPitToMoveFrom;
    this.mancala = mancala;
  }
  id: number;
  playerToMove: number;
  smallPitToMoveFrom: number;
  mancala: Mancala;
}
