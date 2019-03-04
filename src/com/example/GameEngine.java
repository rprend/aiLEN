package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameEngine {
    private List<Card> deck = new ArrayList<>();
    private List<PlayerStrategy> players = new ArrayList<>();
    private List<List<Card>> playerHands = new ArrayList<>();
    private List<Card> discard;
    private List<Integer> ids;
    private Card.Rank currentRank;
    private List<Card> currentPlay;

    public void startUp(int numPlayers) {
        deck = Card.getDeck();
        Collections.shuffle(deck);
        playerHands = new ArrayList<>();
        players = new ArrayList<>();
        ids = new ArrayList<>();
        currentRank = Card.Rank.ACE;
    }


}
