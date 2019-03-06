package com.example;

import java.util.List;

public class Player implements PlayerStrategy {
    @Override
    public void init(int playerId, List<Integer> opponentIds) {

    }

    @Override
    public void receiveInitialCards(List<Card> cards) {

    }

    @Override
    public boolean shouldCallBS(Card.Rank rank, int numPlayed) {
        return false;
    }

    @Override
    public void receiveCards(List<Card> cards) {

    }

    @Override
    public List<Card> playTurn(Card.Rank currentRank) {
        return null;
    }

    @Override
    public void processOpponentActions(List<PlayerTurn> opponentActions) {

    }

    @Override
    public void reset() {

    }
}
