package com.example;

import java.util.List;

/**
 * Represents an player's action on their turn: either they drew a card or they played a card.
 */
public class PlayerTurn {

    /**
     * The player's assigned ID number
     */
    public int playerId;

    /**
     * Number of cards played
     */
    public int numCardsPlayed;

    /**
     * The Rank of the cards played. (should just increment by 1 each time)
     */
    public Card.Rank rank;

    /**
     * The ids of the people who have called BS, the first one recieving the cards.
     * This will be an empty list if nobody has called BS
     */
    public List<Integer> calledBS;

    /**
     * Returns true if and only if at least one person calls BS on the cards played
     * and the cards played weren't what they were declared to be
     * If nobody calls BS, this returns false
     */
    public boolean bsValid;

}
