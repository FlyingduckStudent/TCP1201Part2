package com.example.goboom;


// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

import java.io.Serializable;

public class Card{
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String toString() {
        return rank + suit;
    }
}