package com.example;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EngineTest {

    @Test
    public void getNextPlayerNormal() {
        List<Integer> testPlayers = new ArrayList<>();
        testPlayers.add(0);
        testPlayers.add(1);
        testPlayers.add(2);
        testPlayers.add(3);
        testPlayers.add(4);
        testPlayers.add(5);

        Integer currentPlayer = 3;

        List<Integer> expected = new ArrayList<>();
        expected.add(4);
        expected.add(5);
        expected.add(0);
        expected.add(1);
        expected.add(2);

        assertEquals(expected, GameEngine.getNextPlayers(currentPlayer, testPlayers));

    }

    @Test
    public void getNextPlayerEnd() {
        List<String> testPlayers = new ArrayList<>();
        testPlayers.add("apple");
        testPlayers.add("pear");
        testPlayers.add("banana");
        testPlayers.add("kumquat");
        testPlayers.add("tomato");

        String currentPlayer = "tomato";

        List<String> expected = new ArrayList<>();
        expected.add("apple");
        expected.add("pear");
        expected.add("banana");
        expected.add("kumquat");

        assertEquals(expected, GameEngine.getNextPlayers(currentPlayer, testPlayers));
    }

    @Test
    public void getNextPlayerBegin() {
        List<String> testPlayers = new ArrayList<>();
        testPlayers.add("apple");
        testPlayers.add("pear");
        testPlayers.add("banana");
        testPlayers.add("kumquat");
        testPlayers.add("tomato");

        String currentPlayer = "apple";

        List<String> expected = new ArrayList<>();
        expected.add("pear");
        expected.add("banana");
        expected.add("kumquat");
        expected.add("tomato");

        assertEquals(expected, GameEngine.getNextPlayers(currentPlayer, testPlayers));
    }

    @Test
    public void testDistributeCards() {
        List<PlayerWrapper> players = new ArrayList<>();
        players.add(new PlayerWrapper(0, new Player(), 0, null));
        players.add(new PlayerWrapper(1, new Player(), 1, null));
        players.add(new PlayerWrapper(2, new Player(), 2, null));
        players.add(new PlayerWrapper(3, new Player(), 3, null));

        GameEngine.distributeCards(players, Card.getDeck());

        for (PlayerWrapper player : players)
            assertEquals(13, player.getHand().size());
    }

    @Test
    public void testDistributeCardsOddNum() {
        List<PlayerWrapper> players = new ArrayList<>();
        players.add(new PlayerWrapper(0, new Player(), 0, null));
        players.add(new PlayerWrapper(1, new Player(), 1, null));
        players.add(new PlayerWrapper(2, new Player(), 2, null));
        players.add(new PlayerWrapper(3, new Player(), 3, null));
        players.add(new PlayerWrapper(4, new Player(), 4, null));

        GameEngine.distributeCards(players, Card.getDeck());
        for (int i = 0; i < 2; i++)
            assertEquals(11, players.get(i).getHand().size());

        for (int i = 2; i < players.size(); i++)
            assertEquals(10, players.get(i).getHand().size());

    }
}
