package com.mcl.mancala.game;

import com.mcl.mancala.beans.Mancala;
import com.mcl.mancala.beans.Player;
import lombok.RequiredArgsConstructor;

/*
 * Class to store the movement mechanics when transferring pebbles between the pits.
 *
 * @author Maxim N
 * */

@RequiredArgsConstructor
public class MovementMechanics {
    private final Mancala mancala;
    private final boolean whenCaptureAddToLargePit;
    private boolean lastStoneInLargePit = false;

    public boolean playerMove(int playerNr, int startingSmallPit) {
        Player movingPlayer = mancala.getPlayer(playerNr);
        Player stillPlayer = mancala.getInversePlayer(playerNr);

        if (!movingPlayer.isMove()) {
            return false;
        }

        int pebblesCount = takingCareOfChosenSmallPit(movingPlayer, startingSmallPit - 1);

        if (pebblesCount == 0) {
            return false;
        }

        boolean movingPlayerPits = true;
        while (pebblesCount > 0) {
            Player handleSmallPitsPlayer = movingPlayerPits ? movingPlayer : stillPlayer;
            pebblesCount = addPebblesInPits(pebblesCount, startingSmallPit, handleSmallPitsPlayer);
            startingSmallPit = resetStartingSmallPitToFirst(startingSmallPit);
            movingPlayerPits = !movingPlayerPits;
        }

        decideWhatPlayerMovesNext(movingPlayer, stillPlayer);

        return true;
    }

    private int resetStartingSmallPitToFirst(int startingSmallPit) {
        if (startingSmallPit != 0) {
            startingSmallPit = 0;
        }
        return startingSmallPit;
    }

    private int takingCareOfChosenSmallPit(Player movingPlayer, int i) {
        int pebblesCount = movingPlayer.getSmallPitValue(i);
        movingPlayer.emptySmallPit(i);
        return pebblesCount;
    }

    private void decideWhatPlayerMovesNext(Player movingPLayer, Player stillPlayer) {
        if (lastStoneInLargePit) {
            movingPLayer.setMove(true);
            stillPlayer.setMove(false);
        } else {
            movingPLayer.setMove(false);
            stillPlayer.setMove(true);
        }
    }

    private int addPebblesInPits(int pebblesCount, int startPosition, Player playerWithPitsToFill) {
        int numberOfSmallPits = playerWithPitsToFill.getSmallPits().length;
        for (int pitPosition = startPosition; pitPosition <= numberOfSmallPits; pitPosition++) {
            pebblesCount = fillBigPit(pebblesCount, playerWithPitsToFill, numberOfSmallPits, pitPosition);
            pebblesCount = fillSmallPits(pebblesCount, playerWithPitsToFill, numberOfSmallPits, pitPosition);
            if (pebblesCount == 0) {
                return pebblesCount;
            }
        }
        return pebblesCount;
    }

    private int fillSmallPits(int pebblesCount, Player playerWithPitsToFill, int numberOfSmallPits, int pitPosition) {
        if (pitPosition != numberOfSmallPits) {
            int universalPitPosition = playerWithPitsToFill.isMove() ? pitPosition : getInvertedSmallPit(playerWithPitsToFill, pitPosition);
            playerWithPitsToFill.incrementSmallPit(universalPitPosition);
            pebblesCount--;
            captureOpponentPebbles(pebblesCount, playerWithPitsToFill, pitPosition);
        }
        return pebblesCount;
    }

    private void captureOpponentPebbles(int pebblesCount, Player playerWithPitsToFill, int pitPosition) {
        if (playerWithPitsToFill.isMove()
                && pebblesCount == 0
                && playerWithPitsToFill.getSmallPitValue(pitPosition) == 1) {
            Player opponent = mancala.getInversePlayer(playerWithPitsToFill.getId());
            int invertedPitPosition = getInvertedSmallPit(playerWithPitsToFill, pitPosition);

            int opponentPebbles = takingCareOfChosenSmallPit(opponent, invertedPitPosition);
            playerWithPitsToFill.setPebblesToSmallPit(pitPosition, opponentPebbles + playerWithPitsToFill.getSmallPitValue(pitPosition));

            if (whenCaptureAddToLargePit) {
                playerWithPitsToFill.addToLargePit(playerWithPitsToFill.getSmallPitValue(pitPosition));
                playerWithPitsToFill.emptySmallPit(pitPosition);
            }
        }
    }

    private int getInvertedSmallPit(Player player, int pitPosition) {
        return player.getSmallPits().length - 1 - pitPosition;
    }

    private int fillBigPit(int pebblesCount, Player playerWithPitsToFill, int numberOfSmallPits, int pitPosition) {
        if (pitPosition == numberOfSmallPits && playerWithPitsToFill.isMove()) {
            playerWithPitsToFill.incrementLargePit();
            if (pebblesCount == 1) {
                lastStoneInLargePit = true;
            }
            pebblesCount--;
        }
        return pebblesCount;
    }
}
