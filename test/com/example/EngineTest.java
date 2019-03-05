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
}
