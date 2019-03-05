package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class GameEngine {
    /** The list of player wrapper objects. */
    private List<PlayerWrapper> players = new ArrayList<>();
    /** Integer representations of each player, used to associate players with moves. */
    private List<Integer> ids;

    private List<Card> deck;
    private List<Card> discard;

    private Card.Rank currentRank;
    private List<Card> currentPlay;
    private Map<PlayerWrapper, Integer> scores = new HashMap<>();

    public void startUp(int numPlayers) {
        deck = Card.getDeck();
        players = new ArrayList<>();
        ids = new ArrayList<>();
        discard = new ArrayList<>();
        distributeCards(players, deck);
    }

    /**
     * Plays a full round of BS with the list of playerwrappers.
     */
    public PlayerWrapper playGame() {
        currentRank = Card.Rank.ACE;
        distributeCards(players, Card.getDeck());

        while (true) {
            for (PlayerWrapper player : players) {
                playTurn(player, players, currentRank, discard);
                currentRank = currentRank.next();

                if (player.getHand().size() == 0)
                    return player;
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
        discard.addAll(cardsPlayed);
        int numCardsPlayed = cardsPlayed.size();

        Set<PlayerWrapper> calledBS = new HashSet<>();
        for (PlayerWrapper opponent : players) {
            if (opponent.shouldCallBS(currentRank, numCardsPlayed))
                calledBS.add(opponent);
        }

        if (calledBS.size() != 0) {
            if (cardsMatchRank(cardsPlayed, currentRank)) {
                // Case player was truthful, give cards to first player that called BS
                for (PlayerWrapper nextPlayer : getNextPlayers(currentPlayer, players)) {
                    if (calledBS.contains(nextPlayer)) {
                        nextPlayer.receiveCards(discard);
                        break;
                    }
                }
            } else {
                // Case player was untruthful, they get the discard pile
                currentPlayer.receiveCards(discard);
            }
        }
    }

    /**
     * Checks if cards played  match the matchRank.
     * @param cardsPlayed cards played by the player
     * @param matchRank the rank all the cards should match
     * @return true IF IT IS BS, false if all match matchRank
     */
    public boolean cardsMatchRank(List<Card> cardsPlayed, Card.Rank matchRank) {
        for (Card card : cardsPlayed) {
            if (card.getRank() != matchRank)
                return false;
        }
        return true;
    }

    /**
     * Returns a list of all players EXCEPT currentPlayer whose order is the order they will play cards.
     * This order is used to determine who gets the BS cards if called.
     * @param currentPlayer the player to be removed
     * @param players the list of players to remove the list from
     * @return a list of players with the next player being at the front of the list
     */
    public static <E> List<E> getNextPlayers(E currentPlayer, List<E> players) {

        // TODO: CHECK FOR CONCURRENTMODIFICATION EXCEPTION ASSOCIATED WITH SUBLIST
        int currentPlayerIndex = players.indexOf(currentPlayer);
        List<E> nextPlayers = new ArrayList<>();
        if (currentPlayerIndex + 1 != players.size()) {
            nextPlayers.addAll(players.subList(currentPlayerIndex + 1, players.size()));
        }
        nextPlayers.addAll(players.subList(0, currentPlayerIndex));
        return nextPlayers;
    }

    /**
     * Takes the deck, shuffles it, and gives an even distribution of cards to all players.
     * Players that come first will get slightly more cards if number of players is not divisible by 52
     * @param players the players to give cards to
     * @param deck the deck to distribute from
     */
    public void distributeCards(List<PlayerWrapper> players, List<Card> deck) {
        Collections.shuffle(deck);
        List<List<Card>> passOutCards = new ArrayList<>();
        for (int i = 0; i < deck.size(); i++) {
            passOutCards.get(i % players.size()).add(deck.get(i));
        }
        for (int i = 0; i < players.size(); i++) {
            players.get(i).receiveCards(passOutCards.get(i));
        }
    }

}
