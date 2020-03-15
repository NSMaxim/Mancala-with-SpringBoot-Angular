package com.mcl.mancala.entity;

import com.mcl.mancala.game.Mechanics;
import com.mcl.mancala.repository.entity.MancalaEntity;
import com.mcl.mancala.repository.entity.PlayerState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MancalaEntityTest {

    @Test
    public void checkMancalaEntity() {
        Mechanics gameMechanics = new Mechanics(new Mechanics().start(), false);
        PlayerState player1 = new PlayerState(gameMechanics.getMancala().getPlayer1());
        player1.setId(1L);
        PlayerState player2 = new PlayerState(gameMechanics.getMancala().getPlayer2());
        player2.setId(2L);

        MancalaEntity mancalaEntity = new MancalaEntity(player1, player2);
        mancalaEntity.setId(3L);

        assertEquals(3L, mancalaEntity.getId());
        assertEquals(player1, mancalaEntity.getPlayer1());
        assertEquals(player2, mancalaEntity.getPlayer2());
    }

}
