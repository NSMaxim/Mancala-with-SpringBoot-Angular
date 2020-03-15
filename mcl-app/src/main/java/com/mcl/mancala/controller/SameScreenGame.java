package com.mcl.mancala.controller;

import com.mcl.mancala.beans.GameState;
import com.mcl.mancala.beans.PlayerMove;
import com.mcl.mancala.service.SameScreenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
 * Main controller for the same screen mancala game
 *
 * @author Maxim N
 * */

@Controller
@CrossOrigin
@AllArgsConstructor
public class SameScreenGame {

    private SameScreenService sameScreenService;

    @ResponseBody
    @GetMapping("/new-game")
    public GameState startGame() {
        return sameScreenService.newGame();
    }

    @ResponseBody
    @PostMapping("/make-move")
    public GameState makeMove(@RequestBody PlayerMove playerMove) {
        return sameScreenService.makeMove(playerMove);
    }

}
