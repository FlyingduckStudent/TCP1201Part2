package com.example.goboom;
//import java.util.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
//////Some instructions:
/////1. Change time from 00 to 500 in deck class, pause method to do slow dealing of cards. its set to 00 to speed up testing

//////Class and Structure Design:

///// Each entity is given its own class. Player, Card, Deck.

////// Inheritance usage: Player score extends Player class

////// Composition usage:  the Deck class has a composition relationship with the Card class. The Deck class contains a list of Card objects
////// to represent the collection of cards in the deck. Each Card object represents an individual card in the deck.

////// Interface usage:

////// Instantiation usage: Create new Deck, new Players when the game is restarted

///// Encapsulation usage: Encapsulated the deck and player hands. Using the get method, no changes are made to that instance of data.

////// ArrayList usage: 1. Main deck, for ease of shuffling and drawing while retaining memory
//////                  2. List of players, to arrange them

////// Set usage: Player Hands

////// Map usage: 1. Center pile: LinkedHashMap for better performance of insertion and deletion than treeMap (especially for iteration) and maintaining insertion order,
//////               to determine which player played which card;  at cost of higher memory

//////            2. Player scores: LinkedHashMap, for better performance than TreeMap and for maintaining insertion order. At cost of higher mem usage.

//////changes:  System.out.println(">"); TO System.out.print("> ");
/////           PlayerScore data struc changed to LHM from TreeSet, player hands changed to LHS from Set


public class Main {
    List<Player> players = new ArrayList<>();
    Deck deck = new Deck();
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//
    // Instantiate a new deck outside of loop

    // Create a list for players outside of loop

//
//        while (true) {
//            int command;
//            System.out.println("Deck: " + deck.getCards());
//            System.out.println("Center Pile: " + deck.getCenterPile());
//            System.out.println("\nEnter your choice:");
//            System.out.println("1. Begin Game!");
//            System.out.println("2. Reset Deck & All Hands!");
//            System.out.println("0. Quit");
//            System.out.print("> ");
//
//            command = input.nextInt();
//
//            switch (command) {
//
//                case 1:
//                    // start a clean deck with original arrangement (Instantiation of Deck class)
//                    if (deck.getCards().size() == 52) {
//                        startGame(players, deck);
//                        break;
//                    }
//
//                    else {
//                        System.out.println("Please reset deck and all hands first.");
//                        Tool.pause(1000);
//                        Tool.clearScreen();
//                        break;
//                    }
//
//                case 2:
//                    // start a clean deck with original arrangement (Instantiation of Deck class)
//                    deck = new Deck();
//                    // clear deck and all player hands
//                    for (Player player : players) {
//                        player.getHand().clear();
//                    }
//                    deck.getCenterPile().clear();
//                    // clear the screen
//                    Tool.clearScreen();
//                    // This is to view the players hands after clearing, for testing purposes
//                    for (Player player : players) {
//                        System.out.println(player.getName() + "'s Hand: " + player.getHand());
//                    }
//                    // clear the list of players for the new arrangement of players
//                    players.clear();
//                    break;
//
//                case 0:
//                    System.exit(0);
//                    break; // exit loop and end program
//
//                default:
//                    System.out.println("Invalid choice, try again.");
//                    break;
//
//            }
//
//        }
//
//    }



}
