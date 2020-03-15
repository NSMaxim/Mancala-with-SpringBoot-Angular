package com.mcl.mancala.service;

import com.mcl.mancala.beans.GameState;
import com.mcl.mancala.beans.Mancala;
import com.mcl.mancala.beans.PlayerMove;
import com.mcl.mancala.game.Mechanics;
import com.mcl.mancala.repository.MancalaRepository;
import com.mcl.mancala.repository.PlayerStateRepository;
import com.mcl.mancala.repository.entity.MancalaEntity;
import com.mcl.mancala.repository.entity.PlayerState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SameScreenWithPersistenceServiceTest {

    @Mock
    MancalaRepository mancalaRepository;

    @Mock
    PlayerStateRepository playerStateRepository;

    @InjectMocks
    private SameScreenWithPersistenceService sameScreenWithPersistenceService;

    private Mechanics gameMechanics;

    @Before
    public void setup() {
        gameMechanics = new Mechanics(new Mechanics().start(), false);


        PlayerState player1 = new PlayerState(gameMechanics.getMancala().getPlayer1());
        player1.setId(1L);
        PlayerState player2 = new PlayerState(gameMechanics.getMancala().getPlayer2());
        player2.setId(2L);

        MancalaEntity mancalaEntity = new MancalaEntity(player1, player2);
        mancalaEntity.setId(3L);

        when(mancalaRepository.save(any(MancalaEntity.class))).thenReturn(mancalaEntity);

        when(mancalaRepository.findById(eq(3L))).thenReturn(Optional.of(mancalaEntity));
    }
    @Test
    public void newGame() {
        GameState resultingGameState = new GameState(3L, gameMechanics.getMancala(), gameMechanics.isGameOver(), gameMechanics.whichPlayerWon());

        assertEquals(resultingGameState, sameScreenWithPersistenceService.continueGame(-1));

        verify(mancalaRepository, never()).findById(3L);
        verify(playerStateRepository, never()).findById(1L);
        verify(playerStateRepository, never()).findById(2L);

        verify(mancalaRepository, atLeastOnce()).save(any(MancalaEntity.class));
        verify(playerStateRepository, atLeast(2)).save(any(PlayerState.class));

    }

    @Test
    public void continueExistingGame() {
        GameState resultingGameState = new GameState(3L, gameMechanics.getMancala(), gameMechanics.isGameOver(), gameMechanics.whichPlayerWon());

        assertEquals(resultingGameState, sameScreenWithPersistenceService.continueGame(3L));

        verify(mancalaRepository, atLeastOnce()).findById(3L);
        verify(playerStateRepository, never()).findById(1L);
        verify(playerStateRepository, never()).findById(2L);

        verify(mancalaRepository, never()).save(any(MancalaEntity.class));
        verify(playerStateRepository, never()).save(any(PlayerState.class));
    }

    @Test
    public void makeMove() {
        PlayerMove playerMove = new PlayerMove(new Mechanics().start(), 1, 5);
        gameMechanics.playerMove(1, 5);
        GameState resultingGameState = new GameState(3L, gameMechanics.getMancala(), gameMechanics.isGameOver(), gameMechanics.whichPlayerWon());

        PlayerState player1 = new PlayerState(gameMechanics.getMancala().getPlayer1());
        player1.setId(1L);
        PlayerState player2 = new PlayerState(gameMechanics.getMancala().getPlayer2());
        player2.setId(2L);

        MancalaEntity mancalaEntity = new MancalaEntity(player1, player2);
        mancalaEntity.setId(3L);

        when(mancalaRepository.save(any(MancalaEntity.class))).thenReturn(mancalaEntity);
        when(playerStateRepository.save(any(PlayerState.class))).thenReturn(player1, player2);

        assertEquals(resultingGameState, sameScreenWithPersistenceService.makeMove(playerMove, 3L));

        ArgumentCaptor<MancalaEntity> mancalaEntityArgumentCaptor = ArgumentCaptor.forClass(MancalaEntity.class);
        verify(mancalaRepository, times(1)).save(mancalaEntityArgumentCaptor.capture());

        assertEquals(mancalaEntity, mancalaEntityArgumentCaptor.getValue());

        ArgumentCaptor<PlayerState> playerStateArgumentCaptor = ArgumentCaptor.forClass(PlayerState.class);
        verify(playerStateRepository, times(2)).save(playerStateArgumentCaptor.capture());

        assertEquals(player1, playerStateArgumentCaptor.getAllValues().get(0));
        assertEquals(player2, playerStateArgumentCaptor.getAllValues().get(1));

    }

}
