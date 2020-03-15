package com.mcl.mancala.entity;

import com.mcl.mancala.game.Mechanics;
import com.mcl.mancala.repository.entity.PlayerState;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PlayerStateTest {

    @Test
    public void checkPlayerState() {
        Mechanics gameMechanics = new Mechanics(new Mechanics().start(), false);
        PlayerState player1 = new PlayerState(gameMechanics.getMancala().getPlayer1());
        player1.setId(1L);

        assertEquals(1L, player1.getId());
        assertArrayEquals(new int[]{6,6,6,6,6,6}, player1.decomposeDBSmallPits());
        assertEquals("6;6;6;6;6;6", player1.getSmallPits());
        assertEquals(0, player1.getLargePit());
    }


}
