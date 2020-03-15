package com.mcl.mancala.beans;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*
 * Bean used for storing Player state and utility methods that will modify the state of pit related properties.
 *
 * @author Maxim N
 * */

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Player {
    private int id;
    private int largePit;
    private int[] smallPits;
    private boolean move;

    public void incrementLargePit() {
        largePit++;
    }

    public void incrementSmallPit(int position) {
        smallPits[position]++;
    }

    public void addToLargePit(int pebbles) {
        largePit += pebbles;
    }

    public void setPebblesToSmallPit(int position, int pebbles) {
        smallPits[position] = pebbles;
    }

    public void emptySmallPit(int position) {
        smallPits[position] = 0;
    }

    public void emptySmallPits() {
        smallPits = new int[6];
    }

    public int getSmallPitValue(int position) {
        return smallPits[position];
    }
}
