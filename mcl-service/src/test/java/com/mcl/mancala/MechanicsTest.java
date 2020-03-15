package com.mcl.mancala;

import com.mcl.mancala.beans.Mancala;
import com.mcl.mancala.beans.Player;
import com.mcl.mancala.game.Mechanics;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class MechanicsTest {
    private Mechanics mechanics = new Mechanics();

    @Test
    public void gameStart() {
        mechanics.start();
        Player player1 = mechanics.getMancala().getPlayer1();
        Player player2 = mechanics.getMancala().getPlayer2();

        assertEquals(0, player1.getLargePit());
        assertArrayEquals(new int[]{6, 6, 6, 6, 6, 6}, player1.getSmallPits());

        assertEquals(0, player2.getLargePit());
        assertArrayEquals(new int[]{6, 6, 6, 6, 6, 6}, player2.getSmallPits());

        assertFalse(mechanics.isGameOver());
        assertEquals(-1, mechanics.whichPlayerWon());
    }

    @Test
    public void gameEndPlayer2WinsAndAllPebblesMovedToLargePit() {
        Player player1 = new Player(1, 0, new int[]{0, 0, 0, 0, 0, 1}, true);
        Player player2 = new Player(2, 0, new int[]{1, 1, 1, 1, 1, 1}, false);
        Mancala mancala = new Mancala(player1, player2);
        Mechanics gameMechanics = new Mechanics(mancala, true);

        gameMechanics.playerMove(1, 6);

        assertTrue(gameMechanics.isGameOver());

        assertEquals(1, player1.getLargePit());
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0}, player1.getSmallPits());
        assertEquals(6, player2.getLargePit());
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0}, player2.getSmallPits());

        assertEquals(2, gameMechanics.whichPlayerWon());
    }

    @Test
    public void gameEndPlayer1WinsAndAllPebblesMovedToLargePit() {
        Player player1 = new Player(1, 0, new int[]{1, 1, 1, 1, 1, 1}, false);
        Player player2 = new Player(2, 0, new int[]{0, 0, 0, 0, 0, 1}, true);
        Mancala mancala = new Mancala(player1, player2);
        Mechanics gameMechanics = new Mechanics(mancala, false);

        gameMechanics.playerMove(2, 6);

        assertTrue(gameMechanics.isGameOver());

        assertEquals(6, player1.getLargePit());
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0}, player1.getSmallPits());
        assertEquals(1, player2.getLargePit());
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0}, player2.getSmallPits());

        assertEquals(1, gameMechanics.whichPlayerWon());
    }

    @Test
    public void capturePebblesFromOpposingPit() {
        Player player1 = new Player(1, 0, new int[]{0, 0, 0, 0, 1, 0}, true);
        Player player2 = new Player(2, 0, new int[]{6, 1, 1, 1, 1, 1}, false);
        Mancala mancala = new Mancala(player1, player2);
        Mechanics gameMechanics = new Mechanics(mancala, false);

        gameMechanics.playerMove(1, 5);
        assertEquals(0, player1.getLargePit());
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 7}, player1.getSmallPits());
        assertEquals(0, player2.getLargePit());
        assertArrayEquals(new int[]{0, 1, 1, 1, 1, 1}, player2.getSmallPits());
    }

//    @Ignore("To be enabled in case we automatically add captured pebbles to large pit")
    @Test
    public void capturePebblesFromOpposingPitAndAddToLarge() {

        System.setProperty("CAPTURED_TO_LARGE_PIT", "true");

        Player player1 = new Player(1, 0, new int[]{0, 0, 0, 0, 1, 0}, true);
        Player player2 = new Player(2, 0, new int[]{6, 1, 1, 1, 1, 1}, false);
        Mancala mancala = new Mancala(player1, player2);
        Mechanics gameMechanics = new Mechanics(mancala, true);

        gameMechanics.playerMove(1, 5);
        assertEquals(7, player1.getLargePit());
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0}, player1.getSmallPits());
        assertEquals(0, player2.getLargePit());
        assertArrayEquals(new int[]{0, 1, 1, 1, 1, 1}, player2.getSmallPits());
    }

    @Test
    public void player1MoveSmallPitNr3twice() {
        mechanics.start();
        assertTrue(mechanics.playerMove(1, 3));
        assertFalse(mechanics.playerMove(1, 3));
    }

    @Test
    public void player1MoveSmallPitNr1Then2AndNoMoreMovement() {
        mechanics.start();
        Player player1 = mechanics.getMancala().getPlayer1();
        Player player2 = mechanics.getMancala().getPlayer2();

        assertTrue(mechanics.playerMove(1, 1));
        assertEquals(1, player1.getLargePit());
        assertArrayEquals(new int[]{0, 7, 7, 7, 7, 7}, player1.getSmallPits());
        assertEquals(0, player2.getLargePit());
        assertArrayEquals(new int[]{6, 6, 6, 6, 6, 6}, player2.getSmallPits());

        assertTrue(mechanics.playerMove(1, 2));
        assertEquals(2, player1.getLargePit());
        assertArrayEquals(new int[]{0, 0, 8, 8, 8, 8}, player1.getSmallPits());
        assertEquals(0, player2.getLargePit());
        assertArrayEquals(new int[]{6, 6, 6, 6, 7, 7}, player2.getSmallPits());

        assertFalse(mechanics.playerMove(1, 3));
    }

    @Test
    public void player1MoveSmallPitNr3ThenPlayer2MoveSmallPitNr3() {
        mechanics.start();

        mechanics.start();
        Player player1 = mechanics.getMancala().getPlayer1();
        Player player2 = mechanics.getMancala().getPlayer2();

        assertTrue(mechanics.playerMove(1, 3));
        assertEquals(1, player1.getLargePit());
        assertArrayEquals(new int[]{6, 6, 0, 7, 7, 7}, player1.getSmallPits());
        assertEquals(0, player2.getLargePit());
        assertArrayEquals(new int[]{6, 6, 6, 6, 7, 7}, player2.getSmallPits());

        assertTrue(mechanics.playerMove(2, 3));
        assertEquals(1, player1.getLargePit());
        assertArrayEquals(new int[]{6, 6, 0, 7, 8, 8}, player1.getSmallPits());
        assertEquals(1, player2.getLargePit());
        assertArrayEquals(new int[]{6, 6, 0, 7, 8, 8}, player2.getSmallPits());
    }

}
