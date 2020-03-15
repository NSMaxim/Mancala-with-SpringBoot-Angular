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
    public void gameOver() {
        Mancala mancala = new Mechanics().start();
        mancala.getPlayer1().setSmallPits(new int[]{0,0,0,0,0,1});
        PlayerMove playerMove = new PlayerMove(mancala, 1, 6);

        Mechanics gameMechanics = new Mechanics(mancala, false);
        gameMechanics.playerMove(1, 6);
        GameState resultingGameState = new GameState(1L, mancala, gameMechanics.isGameOver(), gameMechanics.whichPlayerWon());

        assertEquals(resultingGameState, sameScreenService.makeMove(playerMove));
    }

    @Test
    public void makeMove() {
        Mancala mancala = new Mechanics().start();
        PlayerMove playerMove = new PlayerMove(mancala, 1, 5);

        Mechanics gameMechanics = new Mechanics(mancala, false);
        gameMechanics.playerMove(1, 5);
        GameState resultingGameState = new GameState(1L, gameMechanics.getMancala(), gameMechanics.isGameOver(), gameMechanics.whichPlayerWon());

        assertEquals(resultingGameState, sameScreenService.makeMove(playerMove));
    }

}
