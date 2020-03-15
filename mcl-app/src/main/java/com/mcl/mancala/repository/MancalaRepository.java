package com.mcl.mancala.repository;

import com.mcl.mancala.repository.entity.MancalaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * DB repository mancala game
 *
 * @author Maxim N
 * */

@Repository
public interface MancalaRepository extends CrudRepository<MancalaEntity, Long> {
}
