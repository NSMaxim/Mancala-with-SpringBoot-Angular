package com.mcl.mancala.beans;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * Bean used for transferring information about game state between front and back end
 *
 * @author Maxim N
 * */

@Data
public class GameState {
    private final long id;
    private final Mancala mancala;
    private final boolean gameEnded;
    private final int winner;
}
