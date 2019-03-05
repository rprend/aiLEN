package com.example;

import java.util.List;

public class PlayerWrapper {
    private int id;
    private List<Card> hand;
    private PlayerStrategy strategy;

    PlayerWrapper(int id, PlayerStrategy strategy, int playerID, List<Integer> opponentIDs) {
        this.id = id;
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

        List<Card> cardsPlayed = strategy.playTurn(currentRank);

        // Checks for illegal card plays
        // TODO: PUNISH PLAYERS BY SETTING SCORE TO -1 FOR CHEATING
        if (cardsPlayed == null || cardsPlayed.size() == 0)
            throw new SecurityException("Player " + this.id + " played invalid card size!");
        for (Card card : cardsPlayed) {
            if (!hand.contains(card)) {
                throw new SecurityException("Player " + this.id + " played card not in hand!");
            }
        }
        return cardsPlayed;
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
