package com.mcl.mancala.beans;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;

/*
 * Bean to store the state of the two players that take part in the Mancala game
 *
 * @author Maxim N
 * */

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Mancala {
    private Player player1;
    private Player player2;

    public Player getPlayer(int playerNr) {
        return playerNr == 1 ? player1 : player2;
    }

    public Player getInversePlayer(int playerNr) {
        return playerNr == 1 ? player2 : player1;
    }
}
