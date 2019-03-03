package com.example;

import java.util.Objects;

/**
 * Represents an player's action on their turn: either they drew a card or they played a card.
 */
public class PlayerTurn {

    /**
     * The player's assigned ID number
     */
    public int playerId;

    /**
     * If the opponent drew on their turn
     */
    public boolean drewACard;

    /**
     * The card the opponent played on their turn, or null if the opponent didn't play a card.
     */
    public Card playedCard;

    /**
     * When a player plays an "8", they can declare what suit the next player must play to.
     *
     * Set to the declared suit if the player played an "8" card. Otherwise, is set to null.
     */
    public Card.Suit declaredSuit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerTurn that = (PlayerTurn) o;
        return playerId == that.playerId &&
                drewACard == that.drewACard &&
                Objects.equals(playedCard, that.playedCard) &&
                declaredSuit == that.declaredSuit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, drewACard, playedCard, declaredSuit);
    }

    @Override
    public String toString() {
        return playerId + " " + drewACard + " " + playedCard + " " + declaredSuit;
    }
}
