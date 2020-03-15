package com.mcl.mancala.beans;

import com.mcl.mancala.game.Mechanics;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerMoveTest {

    @Test
    public void playerMoveBeanConstructorTest() {
        Mancala mancala = new Mechanics().start();
        PlayerMove playerMove = new PlayerMove(mancala, 1, 2);

        assertEquals(mancala, playerMove.getMancala());
        assertEquals(1, playerMove.getPlayerToMove());
        assertEquals(2, playerMove.getSmallPitToMoveFrom());
    }

    @Test
    public void playerMoveBeanSettersTest() {
        Mancala mancala = new Mechanics().start();
        PlayerMove playerMove = new PlayerMove();
        playerMove.setMancala(mancala);
        playerMove.setPlayerToMove(2);
        playerMove.setSmallPitToMoveFrom(5);

        assertEquals(mancala, playerMove.getMancala());
        assertEquals(2, playerMove.getPlayerToMove());
        assertEquals(5, playerMove.getSmallPitToMoveFrom());
    }

}
