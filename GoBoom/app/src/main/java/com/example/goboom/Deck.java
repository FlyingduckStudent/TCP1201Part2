package com.example.goboom;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Collections;

public class Deck {
    private List<Card> cards = new ArrayList<>();
    private LinkedHashMap<Object,String> centerPile = new LinkedHashMap<>();

    public Deck() {
        String[] suits = { "C", "D", "H", "S" };
        String[] ranks = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "X", "J", "Q", "K" };
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        if (cards.size() == 52) {
            Collections.shuffle(cards);
            // add top card after shuffle to center pile
            AddCard(cards.remove(0).toString());
        } else {
            Tool.clearScreen();
            System.out.println("Please Reset Deck & All Hands First.");
            Tool.pause(1000);
        }
    }

    // this method takes the top card and puts it in the center pile, then deals the cards to the players
    public void dealCards(List<Player> players) {
        int numPlayers = players.size();

        if (cards.size() == 51) {
            // deal cards
            for (int i = 0; i < 7; i++) {
                Tool.clearScreen();
                for (int j = 0; j < numPlayers; j++) {
                    Player currentPlayer = players.get(j);
                    players.get(j).addCard(cards.remove(0));
                    System.out.println(currentPlayer.getName() + "'s Hand: " + currentPlayer.getHand());
                    Tool.pause(00);

                }
            }

        }
    }


    public void AddCard(String card){
        //int position = centerPile.size();
        String position = "*";
        centerPile.put(position, card);
    }

    public void AddCard(String position,String card){
        //int position = centerPile.size();
        centerPile.put(position, card);
    }

    public LinkedHashMap<Object,String> getCenterPile() {
        return centerPile;
    }

}