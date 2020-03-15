package com.mcl.mancala.repository;

import com.mcl.mancala.repository.entity.PlayerState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * DB repository for player state of mancala game
 *
 * @author Maxim N
 * */

@Repository
public interface PlayerStateRepository extends CrudRepository<PlayerState, Long> {
}
