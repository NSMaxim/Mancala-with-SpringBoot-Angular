package com.mcl.mancala.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcl.mancala.beans.GameState;
import com.mcl.mancala.beans.Mancala;
import com.mcl.mancala.beans.PlayerMove;
import com.mcl.mancala.game.Mechanics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SameScreenGameController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void newGame() throws Exception {
        Mancala resultingMancalaState = new Mechanics().start();
        Mechanics gameMechanics = new Mechanics(resultingMancalaState, false);
        GameState resultingGameState = new GameState(1L, resultingMancalaState, gameMechanics.isGameOver(), gameMechanics.whichPlayerWon());

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = get("/new-game")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(resultingGameState)));
    }

    @Test
    public void makeMove() throws Exception {
        PlayerMove playerMove = new PlayerMove(new Mechanics().start(), 1, 5);

        Mancala resultingMancalaState = new Mechanics().start();
        Mechanics gameMechanics = new Mechanics(resultingMancalaState, false);
        gameMechanics.playerMove(1, 5);
        GameState resultingGameState = new GameState(1L, resultingMancalaState, gameMechanics.isGameOver(), gameMechanics.whichPlayerWon());

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = post("/make-move")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(playerMove));

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(resultingGameState)));
    }

}
