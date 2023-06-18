package com.example.goboom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

public class Start_Page extends AppCompatActivity {

    ImageView img_deck,img_c1,img_c2,img_c3,img_c4,img_c5,img_c6,img_c7;

    ArrayList<Integer> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        img_deck = (ImageView) findViewById(R.id.img_deck);
        img_c1 = (ImageView) findViewById(R.id.img_c1);
        img_c2 = (ImageView) findViewById(R.id.img_c2);
        img_c3 = (ImageView) findViewById(R.id.img_c3);
        img_c4 = (ImageView) findViewById(R.id.img_c4);
        img_c5 = (ImageView) findViewById(R.id.img_c5);
        img_c6 = (ImageView) findViewById(R.id.img_c6);
        img_c7 = (ImageView) findViewById(R.id.img_c7);

        img_c1.setVisibility(View.INVISIBLE);
        img_c2.setVisibility(View.INVISIBLE);
        img_c3.setVisibility(View.INVISIBLE);
        img_c4.setVisibility(View.INVISIBLE);
        img_c5.setVisibility(View.INVISIBLE);
        img_c6.setVisibility(View.INVISIBLE);
        img_c7.setVisibility(View.INVISIBLE);

        cards = new ArrayList<>();

        cards.add(102); //spade 2
        cards.add(103); //spade 3
        cards.add(104); //spade 4
        cards.add(111); //spade ace
        cards.add(202); //diamond 2
        cards.add(203); //dia 3
        cards.add(204); //dia 4

        img_deck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.shuffle(cards);

                assignImages(cards.get(0),img_c1);
                assignImages(cards.get(1),img_c2);
                assignImages(cards.get(2),img_c3);
                assignImages(cards.get(3),img_c4);
                assignImages(cards.get(4),img_c5);
                assignImages(cards.get(5),img_c6);
                assignImages(cards.get(6),img_c7);

                img_c1.setVisibility(View.VISIBLE);
                img_c2.setVisibility(View.VISIBLE);
                img_c3.setVisibility(View.VISIBLE);
                img_c4.setVisibility(View.VISIBLE);
                img_c5.setVisibility(View.VISIBLE);
                img_c6.setVisibility(View.VISIBLE);
                img_c7.setVisibility(View.VISIBLE);
            }
        });
    }

    public void assignImages(int card,ImageView image){
        switch(card){
            case 102:
                image.setImageResource(R.drawable.s2);
                break;
            case 103:
                image.setImageResource(R.drawable.s3);
                break;
            case 104:
                image.setImageResource(R.drawable.s4);
                break;
            case 111:
                image.setImageResource(R.drawable.sa);
                break;
            case 202:
                image.setImageResource(R.drawable.d2);
                break;
            case 203:
                image.setImageResource(R.drawable.d3);
                break;
            case 204:
                image.setImageResource(R.drawable.d4);
                break;
        }
    }
}