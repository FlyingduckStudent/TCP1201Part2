package com.example.goboom;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

public class Tool {

    public static String toString(int FirstPlayer, List<Player> players) {
        Player firstplayer = players.get(FirstPlayer);
        return firstplayer.getName();
    }

    // use this method to delay processes
    public static void pause(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // this method clears screen, much longer than C++'s system.cls()
    public static void clearScreen() {

        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            // fallback to clear screen for Unix-based systems
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

    }

    // This is to convert from letters to int. Easier to compare
    public static int ConvertToComparables(char Comparable) {
        int ConvertCardInHand = 0;
        switch (Comparable) {
            case 'A':
                ConvertCardInHand = 14;
                break;
            case 'X':
                ConvertCardInHand = 10;
                break;
            case 'J':
                ConvertCardInHand = 11;
                break;
            case 'Q':
                ConvertCardInHand = 12;
                break;
            case 'K':
                ConvertCardInHand = 13;
                break;
            default:
                ConvertCardInHand = Character.getNumericValue(Comparable);
                break;
        }
        return ConvertCardInHand;
    }

    public static List<Player> ReArrange(List<Player> players, int WinnerIndex) {

        List<Player> PlayerTemporary = new ArrayList<>();
        //System.out.println(players.toString());
        for (int i = WinnerIndex; i < players.size(); i++) {
            PlayerTemporary.add(players.get(i));
        }

        for (int i = 0; i < players.size(); i++) {
            if (!PlayerTemporary.contains(players.get(i))) {
                PlayerTemporary.add(players.get(i));
            }
        }
        return PlayerTemporary;

    }

}
