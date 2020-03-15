export class Pit {
  constructor(
    pebbles: number,
    position: number,
    playerId: number,
    disabled: boolean
  ) {
    this.pebbles = pebbles;
    this.position = position;
    this.playerId = playerId;
    this.disabled = disabled;
  }
  pebbles: number;
  position: number;
  playerId: number;
  disabled: boolean;
}
