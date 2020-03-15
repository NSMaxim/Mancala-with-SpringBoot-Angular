package com.mcl.mancala.repository.entity;

import com.mcl.mancala.beans.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.stream.Collectors;

/*
 * DB entity for a player of the mancala game
 *
 * @author Maxim N
 * */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class PlayerState {

    public static final String SEPARATOR = ";";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int largePit;
    private String smallPits;
    private boolean move;

    public PlayerState(Player player) {
        updatePlayerState(player);
    }

    public void updatePlayerState(Player player) {
        largePit = player.getLargePit();
        smallPits = StringUtils.join(
                Arrays.stream(player.getSmallPits()).boxed().collect(Collectors.toList()),
                SEPARATOR);
        move = player.isMove();
    }

    public int[] decomposeDBSmallPits() {
        return Arrays.stream(getSmallPits().split(SEPARATOR))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}