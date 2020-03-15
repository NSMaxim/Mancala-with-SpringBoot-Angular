package com.mcl.mancala.beans;

import com.mcl.mancala.game.Mechanics;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GameStateTest {

    @Test
    public void gameStateBeanTest() {
        Mancala mancala = new Mechanics().start();
        GameState gameState = new GameState(1L,mancala, false, 2);

        assertEquals(1L, gameState.getId());
        assertEquals(mancala, gameState.getMancala());
        assertEquals(2, gameState.getWinner());
        assertFalse(gameState.isGameEnded());
    }

}
