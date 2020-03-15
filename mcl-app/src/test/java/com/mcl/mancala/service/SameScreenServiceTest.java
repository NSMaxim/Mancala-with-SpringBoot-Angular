package com.mcl.mancala.service;

import com.mcl.mancala.beans.GameState;
import com.mcl.mancala.beans.Mancala;
import com.mcl.mancala.beans.PlayerMove;
import com.mcl.mancala.game.Mechanics;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SameScreenServiceTest {

    private SameScreenService sameScreenService = new SameScreenService();

    @Test
    public void createNewGame() {
        Mancala resultingMancalaState = new Mechanics().start();
        Mechanics gameMechanics = new Mechanics(resultingMancalaState, false);
        GameState resultingGameState = new GameState(1L, resultingMancalaState, gameMechanics.isGameOver(), gameMechanics.whichPlayerWon());

        assertEquals(resultingGameState, sameScreenService.newGame());
    }

    @Test
    public void makeMove() {
        PlayerMove playerMove = new PlayerMove(new Mechanics().start(), 1, 5);

        Mancala resultingMancalaState = new Mechanics().start();
        Mechanics gameMechanics = new Mechanics(resultingMancalaState, false);
        gameMechanics.playerMove(1, 5);
        GameState resultingGameState = new GameState(1L, resultingMancalaState, gameMechanics.isGameOver(), gameMechanics.whichPlayerWon());

        assertEquals(resultingGameState, sameScreenService.makeMove(playerMove));
    }

}
