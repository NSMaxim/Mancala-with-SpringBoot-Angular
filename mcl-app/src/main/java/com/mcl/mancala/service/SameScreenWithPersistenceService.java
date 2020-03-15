package com.mcl.mancala.service;

import com.mcl.mancala.beans.GameState;
import com.mcl.mancala.beans.Mancala;
import com.mcl.mancala.beans.Player;
import com.mcl.mancala.beans.PlayerMove;
import com.mcl.mancala.game.Mechanics;
import com.mcl.mancala.repository.MancalaRepository;
import com.mcl.mancala.repository.PlayerStateRepository;
import com.mcl.mancala.repository.entity.MancalaEntity;
import com.mcl.mancala.repository.entity.PlayerState;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
 * A service to handle the actions for the mancala game with DB persistence
 *
 * @author Maxim N
 * */

@RequiredArgsConstructor
@Service
@Log
public class SameScreenWithPersistenceService {

    private final MancalaRepository mancalaRepository;
    private final PlayerStateRepository playerStateRepository;

    @Value("${CAPTURED_TO_LARGE_PIT:false}")
    private boolean whenCapturedAddToLargePit;

    public GameState continueGame(long id) {
        log.info(String.format("Trying to continue game with id %o ", id));
        Optional<MancalaEntity> repoMancalaOptional = mancalaRepository.findById(id);
        return repoMancalaOptional.map(this::getExistingGameState).orElseGet(this::newGame);
    }

    public GameState newGame() {
        Mechanics mechanics = new Mechanics();
        mechanics.start();

        GameState newGameState = getUpdateGameState(
                new PlayerState(mechanics.getMancala().getPlayer1()),
                new PlayerState(mechanics.getMancala().getPlayer2()));

        log.info(String.format("Started new game with id %o ", newGameState.getId()));
        return newGameState;
    }

    public GameState makeMove(PlayerMove playerMove, long id) {
        Optional<MancalaEntity> repoMancalaOptional = mancalaRepository.findById(id);

        if (repoMancalaOptional.isPresent()) {
            MancalaEntity mancalaEntityFromRepo = repoMancalaOptional.get();
            GameState gameState = getExistingGameState(mancalaEntityFromRepo);
            Mechanics mechanics = new Mechanics(gameState.getMancala(), whenCapturedAddToLargePit);
            mechanics.playerMove(playerMove.getPlayerToMove(), playerMove.getSmallPitToMoveFrom());

            mancalaEntityFromRepo.getPlayer1().updatePlayerState(mechanics.getMancala().getPlayer1());
            mancalaEntityFromRepo.getPlayer2().updatePlayerState(mechanics.getMancala().getPlayer2());

            log.info(String.format("Updating game with id %o ", mancalaEntityFromRepo.getId()));
            return getUpdateGameState(mancalaEntityFromRepo.getPlayer1(), mancalaEntityFromRepo.getPlayer2());
        } else {
            //TODO: this shouldn't really happen in real life
            return newGame();
        }
    }

    private GameState getUpdateGameState(PlayerState player1, PlayerState player2) {
        log.info(String.format("Saving player with id %o ", player1.getId()));
        PlayerState player1State = playerStateRepository.save(player1);

        log.info(String.format("Saving player with id %o ", player2.getId()));
        PlayerState player2State = playerStateRepository.save(player2);

        MancalaEntity mancalaEntity = new MancalaEntity(player1State, player2State);

        MancalaEntity repoMancala = mancalaRepository.save(mancalaEntity);
        log.info(String.format("Saving game with id %o ", repoMancala.getId()));
        return getExistingGameState(repoMancala);
    }

    private GameState getExistingGameState(MancalaEntity repoMancalaEntity) {
        Mechanics mechanics = mechanicsFromRepoItem(repoMancalaEntity);
        return new GameState(repoMancalaEntity.getId(), mechanics.getMancala(), mechanics.isGameOver(), mechanics.whichPlayerWon());
    }

    private Mechanics mechanicsFromRepoItem(MancalaEntity repoMancala) {
        PlayerState player1 = repoMancala.getPlayer1();
        PlayerState player2 = repoMancala.getPlayer2();

        return new Mechanics(new Mancala(
                new Player(1, player1.getLargePit(), player1.decomposeDBSmallPits(), player1.isMove()),
                new Player(2, player2.getLargePit(), player2.decomposeDBSmallPits(), player2.isMove())));
    }

}
