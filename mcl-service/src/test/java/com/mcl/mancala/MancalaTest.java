package com.mcl.mancala;

import com.mcl.mancala.beans.Mancala;
import com.mcl.mancala.beans.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class MancalaTest {

    @Test
    public void mancalaComponentsReview() {
        int player1Id = 1;
        int player1largePit = 2;
        int[] player1smallPits = new int[]{3, 4, 5, 6, 7, 8};
        boolean player1move = true;
        Player player1 = new Player(player1Id, player1largePit, player1smallPits, player1move);

        int player2Id = 2;
        int player2largePit = 5;
        int[] player2smallPits = new int[]{3, 5, 5, 0, 0, 8};
        boolean player2move = false;
        Player player2 = new Player(player2Id, player2largePit, player2smallPits, player2move);

        Mancala mancala = new Mancala(player1, player2);

        assertSame(player1, mancala.getPlayer1());
        assertSame(player2, mancala.getPlayer2());

        assertSame(player1, mancala.getPlayer(1));
        assertSame(player2, mancala.getInversePlayer(1));
    }

}
