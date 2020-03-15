package com.mcl.mancala;

import com.mcl.mancala.beans.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player dummyPLayer = new Player(1, 1, new int[]{1, 1, 1, 1, 1, 1}, false);

    @Test
    public void playerThatCanMove() {
        int playerId = 1;
        int largePit = 2;
        int[] smallPits = new int[]{3, 4, 5, 6, 7, 8};
        boolean move = true;
        Player playerToTest = new Player(playerId, largePit, smallPits, move);

        assertEquals(playerId, playerToTest.getId());
        assertEquals(largePit, playerToTest.getLargePit());
        assertArrayEquals(smallPits, playerToTest.getSmallPits());
        assertTrue(playerToTest.isMove());
    }

    @Test
    public void playerThatCantMove() {
        int playerId = 2;
        int largePit = 5;
        int[] smallPits = new int[]{3, 5, 5, 0, 0, 8};
        boolean move = false;
        Player playerToTest = new Player(playerId, largePit, smallPits, move);

        assertEquals(playerId, playerToTest.getId());
        assertEquals(largePit, playerToTest.getLargePit());
        assertArrayEquals(smallPits, playerToTest.getSmallPits());
        assertFalse(playerToTest.isMove());
    }

    @Test
    public void doPlayerAddToLargePit() {
        dummyPLayer.addToLargePit(44);
        assertEquals(45, dummyPLayer.getLargePit());
    }

    @Test
    public void doPlayerEmptySmallPit() {
        dummyPLayer.emptySmallPit(1);
        assertEquals(0, dummyPLayer.getSmallPitValue(1));
    }

    @Test
    public void doPlayerIncrementLargePit() {
        dummyPLayer.incrementLargePit();
        assertEquals(2, dummyPLayer.getLargePit());
    }

    @Test
    public void doPlayerIncrementSmallPit() {
        dummyPLayer.incrementSmallPit(2);
        assertEquals(2, dummyPLayer.getSmallPitValue(2));
    }

    @Test
    public void doPlayerGetSmallPitValue() {
        dummyPLayer.setPebblesToSmallPit(3, 6);
        assertEquals(6, dummyPLayer.getSmallPitValue(3));
    }

}
