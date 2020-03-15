package com.mcl.mancala.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/*
 * Bean used for handling the game movements that are done from front end side
 *
 * @author Maxim N
 * */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerMove {
    private Mancala mancala;
    private int playerToMove;
    private int smallPitToMoveFrom;
}
