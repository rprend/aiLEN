package com.example;

import java.util.*;

public class GameEngine {
    /** The list of player wrapper objects. */
    private List<PlayerWrapper> players = new ArrayList<>();
    /** Integer representations of each player, used to associate players with moves. */
    private List<Integer> ids;

    private List<Card> deck = new ArrayList<>();
    private List<Card> discard;

    private Card.Rank currentRank;
    private List<Card> currentPlay;
    private Map<PlayerWrapper, Integer> scores = new HashMap<>();

    public void startUp(int numPlayers) {
        deck = Card.getDeck();
        Collections.shuffle(deck);
        players = new ArrayList<>();
        ids = new ArrayList<>();
        currentRank = Card.Rank.ACE;
    }

    /**
     * Plays a full round of BS with the list of playerwrappers.
     */
    public void playGame() {
        while (getWinner(players) == null) {
            for (PlayerWrapper player : players) {
                playTurn(player, players, currentRank, discard);
                currentRank = currentRank.next();
            }
        }
    }

    /**
     * Plays a single turn of a player, passing information on to other players for if they should call BS.
     * @param currentPlayer the players playing cards
     * @param players the list of all other players
     * @param currentRank the current rank that needs to be matched
     * @param discard the pile of cards to plop the cards onto
     *                this will be passed on to the next player in line that calls BS
     */
    public void playTurn(PlayerWrapper currentPlayer, List<PlayerWrapper> players, Card.Rank currentRank, List<Card> discard) {
        // TODO: INCORPORATE OTHER PLAYER TURN TRACKER
        List<Card> cardsPlayed = currentPlayer.playTurn(currentRank, new ArrayList<>());
        int numCardsPlayed = cardsPlayed.size();

        Set<PlayerWrapper> calledBS = new HashSet<>();
        for (PlayerWrapper opponent : players) {
            if (opponent.shouldCallBS(currentRank, numCardsPlayed))
                calledBS.add(opponent);
        }

        if (calledBS.size() != 0) {
            if (isBS(cardsPlayed, currentRank)) {
                currentPlayer.receiveCards(cardsPlayed);
                return;
            } else {
                for (PlayerWrapper nextPlayer : getNextPlayers(currentPlayer, players)) {
                    if (calledBS.contains(nextPlayers)) {
                        nextPlayer.receiveCards(cardsPlayed);
                        break;
                    }
                }
            }
        }
        discard.addAll(cardsPlayed);
    }

    // TODO: CHANGE BOOLEAN TO OPPOSITE?
    /**
     * Checks if cards played don't match the matchRank.
     * @param cardsPlayed cards played by the player
     * @param matchRank the rank all the cards should match
     * @return true IF IT IS BS, false if all match matchRank
     */
    public boolean isBS(List<Card> cardsPlayed, Card.Rank matchRank) {
        for (Card card : cardsPlayed) {
            if (card.getRank() != matchRank)
                return true;
        }
        return false;
    }


    public PlayerWrapper getWinner(List<PlayerWrapper> players) {
        // TODO: IMPLEMENT
        return null;
    }

    /**
     * Returns a list of all players EXCEPT currentPlayer whose order is the order they will play cards.
     * This order is used to determine who gets the BS cards if called.
     * @param currentPlayer the player to be removed
     * @param players the list of players to remove the list from
     * @return a list of players with the next player being at the front of the list
     */
    public List<PlayerWrapper> getNextPlayers(PlayerWrapper currentPlayer, List<PlayerWrapper> players) {
        int currentPlayerIndex = players.indexOf(currentPlayer);
        List<PlayerWrapper> nextPlayers = new ArrayList<>();
        if (currentPlayerIndex + 1 != players.size()) {
            nextPlayers.addAll(players.subList(currentPlayerIndex + 1, players.size()));
        }
        nextPlayers.addAll(players.subList(0, currentPlayerIndex));
        return nextPlayers;
    }

    /**
     * Takes the deck, shuffles, it, and then gives an even distribution of cards to all players.
     * @param players the players to give cards to
     * @param deck the deck to distribute from
     */
    public void distributeCards(List<PlayerWrapper> players, List<Card> deck) {
        // TODO: IMPLEMENT
    }

}
