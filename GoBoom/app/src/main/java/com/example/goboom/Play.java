package com.example.goboom;

import java.util.List;
// import java.util.Map;
import java.util.Map.Entry;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.Collections;
//import java.util.Iterator;
import java.util.LinkedHashMap;

public class Play {

    // public static List<Integer> score = new ArrayList<>();

    // This method is for specific card, meaning it will only take one card
    static char CardPlay(String CardInHand, LinkedHashMap<Object, String> CenterDeck) {
        // eg : 'XK' it will take 'X' and convert it to int so that you can compare it
        // later on
        Entry<Object, String> entry = CenterDeck.entrySet().iterator().next();
        int ConvertedCardInHand = Tool.ConvertToComparables(CardInHand.charAt(0));
        int ConvertedCardInCentreDeck = Tool.ConvertToComparables(entry.getValue().charAt(0));
        // If the card in hand is the same suit as the centre card. Takes the first
        // character from the string
        // eg : 'XK' it will take 'X' and compare the suit in the centerdeck. if same it
        // will return 'S'
        if (CardInHand.charAt(1) == entry.getValue().charAt(1)) {
            return 'S';
            // If the card in hand is higher in precedence than the card in centredeck
            // eg : 'XK' it will take 'X', convert it to int, then compare the card with
            // centredeck
        } else if (ConvertedCardInHand == ConvertedCardInCentreDeck) {
            return 'P';
            // If nothing it will return 'N'
        } else {
            return 'N';
        }

    }

    // this overloaded method is to compare the player cards with centre deck. it
    // takes the entire card array not a specific card
    // others all the same ny

    // static char CardPlay(Set<Card> CardInHand, String CenterDeck) {
    // int ConvertedCardInMainDeck =
    // Tool.ConvertToComparables(CenterDeck.charAt(0));
    // for (int i = 0; i < CardInHand.size(); i++) {
    // int ConvertedCardInHand =
    // Tool.ConvertToComparables(CardInHand.get(i).toString().charAt(0));
    // if (CardInHand.get(i).toString().charAt(1) == CenterDeck.charAt(1)) {
    // return 'S';
    // } else if (ConvertedCardInHand == ConvertedCardInMainDeck) {
    // return 'P';
    // }
    // }
    // return 'N';
    // }

    static char CardPlay(LinkedHashSet<Card> CardInHand, LinkedHashMap<Object, String> CenterDeck) {

        // getting the first key value pair
        Entry<Object, String> entry = CenterDeck.entrySet().iterator().next();
        int ConvertedCardInCentreDeck = Tool.ConvertToComparables(entry.getValue().charAt(0));

        for (Card card : CardInHand) {
            // System.out.println(card);
            int ConvertedCardInHand = Tool.ConvertToComparables(card.toString().charAt(0));
            if (card.toString().charAt(1) == entry.getValue().charAt(1)) {
                return 'S';
            } else if (ConvertedCardInHand == ConvertedCardInCentreDeck) {
                return 'P';
            }
        }
        return 'N';

    }

    // this is to see whether the card inputted by the player is in the player deck
    // or not

    static boolean CardContains(String CardPlayed, LinkedHashSet<Card> CardInHand) {
        for (Card card : CardInHand) {
            if (card.toString().equals(CardPlayed)) {
                return true;
            }
        }
        return false;
    }

    // if the main deck is not empty this will be executed to draw cards from main
    // deck and add them to player
    // it will remove from maindeck byt the way
    static void DrawMain(LinkedHashSet<Card> CardInHand, Deck deck) {
        if (!deck.getCards().isEmpty()) {
            // deck.getCards().size() - 1 <-- this is to get the top part of the array
            CardInHand.add(deck.getCards().get(deck.getCards().size() - 1));
            deck.getCards().remove(deck.getCards().size() - 1);
        }
    }

    // this is to get the index value of a card, from card array. Needed it to
    // remove the card from the player's hand
    // after they are done playing their round
    static int IndexOf(LinkedHashSet<Card> Cards, String CardInHand) {
        int index = 0;
        for (Card card : Cards) {
            if (card.toString().equals(CardInHand)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    // first must convert to number format because the cards are in alphanumeric
    // form
    // and then arrange them in an array. find the highest value card
    // then return back the highest
    // later must compare which player got the highest card in the main method

    public static List<Integer> HighestPlayerIndex(LinkedHashMap<Object, String> Cards) {

        int Highest, HighestCard;
        List<Integer> WinnerInfo = new ArrayList<>();

        Entry<Object, String> entry = Cards.entrySet().iterator().next();
        char LeadingSuit = entry.getValue().charAt(1);
        int LeadingCard = Tool.ConvertToComparables(entry.getValue().charAt(0));
        List<Integer> NumberFormat = new ArrayList<>();

        if (Cards.size() == 4) {
            for (Object Card : Cards.values()) {
                if (Card.toString().charAt(1) == LeadingSuit) {
                    if (LeadingCard <= Tool.ConvertToComparables(Card.toString().charAt(0))) {
                        NumberFormat.add(Tool.ConvertToComparables(Card.toString().charAt(0)));
                    } else{
                        NumberFormat.add(Tool.ConvertToComparables(Card.toString().charAt(0)));
                    }
                } else {
                    NumberFormat.add(0);
                }
            }
        } else if (Cards.size() == 5) {
            for (Object Card : Cards.values()) {
                // System.out.println("Test 2");
                if (Card.toString().charAt(1) == LeadingSuit) {

                    if (LeadingCard <= Tool.ConvertToComparables(Card.toString().charAt(0))) {
                        NumberFormat.add(Tool.ConvertToComparables(Card.toString().charAt(0)));
                    }else{
                        NumberFormat.add(Tool.ConvertToComparables(Card.toString().charAt(0)));
                    }
                } else {
                    NumberFormat.add(0);
                }
            }
        }

        // System.out.println(NumberFormat.toString());
        if (!NumberFormat.isEmpty()) {
            if (Cards.size() == 5) {
                NumberFormat.remove(0);
            }
            Highest = NumberFormat.indexOf(Collections.max(NumberFormat));
            HighestCard = Collections.max(NumberFormat);
        } else {
            HighestCard = 0;
            Highest = 0;
        }

        // score.add(HighestCard);
        // System.out.println(Cards.values());
        WinnerInfo.add(Highest);
        WinnerInfo.add(HighestCard);

        // System.out.println(Highest);
        return WinnerInfo;

    }
}

// } else {
// for(Object Card : Cards){
// if(Card.toString().charAt(0) == LeadingSuit){
// if(LeadingCard <= Tool.ConvertToComparables(Card.toString().charAt(0))){
// NumberFormat.add(Tool.ConvertToComparables(Card.toString().charAt(0)));
// }else{
// NumberFormat.add(Tool.ConvertToComparables(Card.toString().charAt(0)));
// }
// }else{
// NumberFormat.add(0);
// }
// }
// }