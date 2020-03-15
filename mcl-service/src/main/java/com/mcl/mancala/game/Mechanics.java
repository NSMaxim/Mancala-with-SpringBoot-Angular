package com.mcl.mancala.game;

import com.mcl.mancala.beans.Mancala;
import com.mcl.mancala.beans.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.Arrays;
import java.util.logging.Logger;

/*
 * Class to store most of the game mechanics like game state / who won and movement initialization
 *
 * @author Maxim N
 * */

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Log
public class Mechanics {

    @NonNull
    private Mancala mancala;
    @NonNull
    private boolean whenCapturedAddToLargePit;

    public Mechanics(Mancala mancala) {
        this(mancala, false);
    }

    public Mancala start() {
        mancala = new Mancala(
                new Player(1, 0, new int[]{6, 6, 6, 6, 6, 6}, true),
                new Player(2, 0, new int[]{6, 6, 6, 6, 6, 6}, false)
        );
        return mancala;
    }

    public boolean isGameOver() {
        boolean player1PitEmpty = Arrays.stream(mancala.getPlayer1().getSmallPits()).noneMatch(element -> element > 0);
        boolean player2PitEmpty = Arrays.stream(mancala.getPlayer2().getSmallPits()).noneMatch(element -> element > 0);

        allPebblesToLargePitIfOpposingPlayerIsEmpty(player1PitEmpty, mancala.getPlayer2());
        allPebblesToLargePitIfOpposingPlayerIsEmpty(player2PitEmpty, mancala.getPlayer1());

        return player1PitEmpty || player2PitEmpty;
    }

    private void allPebblesToLargePitIfOpposingPlayerIsEmpty(boolean opposingPlayerEmpty, Player playerWithSmallPits) {
        if (opposingPlayerEmpty) {
            Arrays.stream(playerWithSmallPits.getSmallPits()).forEach(playerWithSmallPits::addToLargePit);
            playerWithSmallPits.emptySmallPits();
        }
    }

    public int whichPlayerWon() {
        if (!isGameOver()) {
            return -1;
        }

        if (mancala.getPlayer1().getLargePit() > mancala.getPlayer2().getLargePit()) {
            return mancala.getPlayer1().getId();
        } else {
            return mancala.getPlayer2().getId();
        }
    }

    public boolean playerMove(int playerId, int startingSmallPit) {
        return new MovementMechanics(mancala, whenCapturedAddToLargePit).playerMove(playerId, startingSmallPit);
    }
}
