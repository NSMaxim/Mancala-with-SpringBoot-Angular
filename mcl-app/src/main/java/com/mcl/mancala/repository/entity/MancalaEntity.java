package com.mcl.mancala.repository.entity;

import lombok.*;

import javax.persistence.*;

/*
 * DB entity for mancala game
 *
 * @author Maxim N
 * */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class MancalaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private PlayerState player1;
    @OneToOne
    private PlayerState player2;

    public MancalaEntity(PlayerState player1, PlayerState player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

}
