package com.mcl.mancala.service;

import com.mcl.mancala.beans.GameState;
import com.mcl.mancala.beans.PlayerMove;
import com.mcl.mancala.game.Mechanics;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*
 * A service to handle the actions for the mancala game
 *
 * @author Maxim N
 * */

@Service
@Log
public class SameScreenService {

    @Value("${CAPTURED_TO_LARGE_PIT:false}")
    private boolean whenCapturedAddToLargePit;

    public GameState newGame() {
        Mechanics mechanics = new Mechanics();

        log.info("New game started");
        log.info(String.format("Game running with CAPTURED_TO_LARGE_PIT = %s", whenCapturedAddToLargePit));

        return new GameState(1L,
                mechanics.start(),
                mechanics.isGameOver(),
                mechanics.whichPlayerWon());
    }

    public GameState makeMove(PlayerMove playerMove) {
        Mechanics mechanics = new Mechanics(playerMove.getMancala(), whenCapturedAddToLargePit);
        mechanics.playerMove(playerMove.getPlayerToMove(), playerMove.getSmallPitToMoveFrom());

        GameState gameStateAfterMove = new GameState( 1L,
                mechanics.getMancala(),
                mechanics.isGameOver(),
                mechanics.whichPlayerWon());

        if (gameStateAfterMove.isGameEnded()) {
            log.info(String.format("Game ended with the winner being player %d with a total of %d pebbled",
                    gameStateAfterMove.getWinner(),
                    mechanics.getMancala().getPlayer(gameStateAfterMove.getWinner()).getLargePit()));
        } else {
            log.info(String.format("Move was made by player %d from small pit %d", playerMove.getPlayerToMove(), playerMove.getSmallPitToMoveFrom()));
        }

        return gameStateAfterMove;
    }

}
