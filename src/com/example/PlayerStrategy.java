package com.example;

import java.util.List;

/**
 * A contract for how a Crazy8's player will interact with a Crazy8's game engine.
 */
public interface PlayerStrategy {

    /**
     * Gives the player their assigned id, as well as a list of the opponents' assigned ids.
     *
     * @param playerId The id for this player
     * @param opponentIds A list of ids for this player's opponents
     */
    void init(int playerId, List<Integer> opponentIds);

    /**
     * Called at the very beginning of the game to deal the player their initial cards.
     *
     * @param cards The initial list of cards dealt to this player
     */
    void receiveInitialCards(List<Card> cards);

    /**
     * Called after each card played to ask if the player wants to play bs.
     * The next person that's going to play that called BS receives the cards if BS fails.
     * @param rank the rank played
     * @param numPlayed the number of cards played
     * @return the decision of the bot AI
     */
    boolean shouldCallBS(Card.Rank rank, int numPlayed);

    /**
     * Called whenever the bot says BS when the other player was truthful.
     * @param cards the cards from the pile to be received by the player
     */
    void receiveCards(List<Card> cards);

    /**
     * The main function called for an AI's turn.
     * @param currentRank the current rank needed to be played
     * @return the list of cards to be played (the size tells GameEngine how many cards are played)
     */
    List<Card> playTurn(Card.Rank currentRank);

    /**
     * Called at the very beginning of this player's turn to give it context of what its opponents
     * chose to do on each of their turns.
     *
     * @param opponentActions A list of what the opponents did on each of their turns
     */
    void processOpponentActions(List<PlayerTurn> opponentActions);

    /**
     * Called when the game is being reset for the next round.
     */
    void reset();

}
