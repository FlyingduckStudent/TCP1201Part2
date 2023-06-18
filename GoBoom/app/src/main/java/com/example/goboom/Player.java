package com.example.goboom;

import java.io.Serializable;
import java.util.*;

public class Player{
    private String name;
    private LinkedHashSet<Card> hand = new LinkedHashSet<>();
    public static LinkedHashMap<String, Integer> scores = new LinkedHashMap<>();


    public static LinkedHashMap<String, Integer> GetScore2(){
        return scores;
    }

    public Player(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void removeCard(String card){

        Card CardToRemove = null;
        for(Card c : hand){
            if(c.toString().equals(card)){
                CardToRemove = c;
                break;
            }
        }
        hand.remove(CardToRemove);
    }

    public String getName() {
        return name;
    }

    public LinkedHashSet<Card> getHand() {
        return hand;
    }


    public static List<Player> CreatePlayersList(int n, List<Player> players) {
        players.add(new Player("Player A"));
        players.add(new Player("Player B"));
        players.add(new Player("Player C"));
        players.add(new Player("Player D"));
        // Here we are using rotate() method to rotate the element by distance n
        for (Player player : players) {
            scores.put(player.getName(), 0);
            // the '0' is to set the score as zero. alternative way for clarity is to
            // set int score = 0, but that would be a redundant line
        }
        Collections.rotate(players, n);
        return players;
    }



    public static void setScore(List<Player> players) {
        for(int i = 0; i < players.size(); i++){
            if(!players.get(i).getHand().isEmpty()){
                int CummulativePoints = 0;
                for(Object hand : players.get(i).getHand()){

                    int newPoints = Tool.ConvertToComparables(hand.toString().charAt(0));
                    if (newPoints == 11) {
                        newPoints -= 1;
                    }
                    if (newPoints == 12) {
                        newPoints -= 2;
                    }
                    if (newPoints == 13) {
                        newPoints -= 3;
                    }
                    if (newPoints == 14) {
                        newPoints -= 13;
                    }
                    CummulativePoints =   CummulativePoints  + newPoints;

                }
                scores.replace(Tool.toString(i, players), CummulativePoints);
            }
        }

    }
    public static class PlayerScores extends Player {
        //private int score;

        public PlayerScores(String name) {
            super(name); // access name from the parent class constructor
        }

        // public void setScore(int score) {
        //     //this.score = score;
        //     scores.put(getName(), score);
        // }

        //use this if you want to test a certain players score
        // public int getScore() {
        // return score;
        // }

        public static void displayScores() {
            // System.out.println(scores);
            System.out.print("Score: ");
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                System.out.print(entry.getKey() + " = " + entry.getValue() + " | ");
            }
            System.out.println();
        }

        public static ArrayList<String> GetScores() {
            ArrayList<String> Score = new ArrayList<>();
            // System.out.println(scores);
            System.out.print("Score: ");
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                Score.add(entry.getKey() + " = " + entry.getValue() + " | ");
            }
            return Score;
        }


    }
}