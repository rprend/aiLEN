package com.example;

import java.util.List;

public class PlayerWrapper {
    private List<Card> hand;
    private PlayerStrategy strategy;

    PlayerWrapper(PlayerStrategy strategy, int playerID, List<Integer> opponentIDs) {
        this.strategy = strategy;
        strategy.init(playerID, opponentIDs);
    }

    /**
     * Passes the original hand of cards off to the player strategy wrapped within.
     * @param initialCards the initial hand of cards
     */
    public void receiveInitialCards(List<Card> initialCards) {
        strategy.receiveInitialCards(initialCards);
    }

    /**
     * Passes on all arguments for a player's turn to the PlayerStrategy
     * @param currentRank the rank needed to be played
     * @param opponentActions A list of all previous actions ever since the last opponent played
     * @return a list of cards that the playerstrategy wants to play, the number being reported to the other players
     */
    public List<Card> playTurn(Card.Rank currentRank, List<PlayerTurn> opponentActions) {
        strategy.processOpponentActions(opponentActions);
        return strategy.playTurn(currentRank);
    }

    public boolean shouldCallBS(Card.Rank rank, int numPlayed) {
        return strategy.shouldCallBS(rank, numPlayed);
    }

    public void receiveCards(List<Card> cards) {
        hand.addAll(cards);
        strategy.receiveCards(cards);
    }

    public void reset() {
        strategy.reset();
    }

}
