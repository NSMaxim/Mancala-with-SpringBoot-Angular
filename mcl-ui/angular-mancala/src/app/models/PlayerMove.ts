import { Mancala } from "./Mancala";

export class PlayerMove {
  constructor(
    mancala: Mancala,
    playerToMove: number,
    smallPitToMoveFrom: number,
    id?: number
  ) {
    this.mancala = mancala;
    this.playerToMove = playerToMove;
    this.smallPitToMoveFrom = smallPitToMoveFrom;
    this.id = id;
  }
  id: number;
  mancala: Mancala;
  playerToMove: number;
  smallPitToMoveFrom: number;
}
