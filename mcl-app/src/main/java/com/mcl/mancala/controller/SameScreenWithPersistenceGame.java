package com.mcl.mancala.controller;

import com.mcl.mancala.beans.GameState;
import com.mcl.mancala.beans.PlayerMove;
import com.mcl.mancala.service.SameScreenWithPersistenceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/*
 * Main controller for the same screen mancala game with DB persistence
 *
 * @author Maxim N
 * */

@Controller
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/with-persistence")
public class SameScreenWithPersistenceGame {

    private SameScreenWithPersistenceService sameScreenWithPersistenceService;

    @ResponseBody
    @GetMapping("/continue")
    public GameState continueGame(long id) {
        if (id == -1) {
            return sameScreenWithPersistenceService.newGame();
        } else {
            return sameScreenWithPersistenceService.continueGame(id);
        }
    }

    @ResponseBody
    @PostMapping("/make-move")
    public GameState makeMove(@NotNull long id,
                              @RequestBody PlayerMove playerMove) {
        return sameScreenWithPersistenceService.makeMove(playerMove, id);
    }

}
