package com.example.goboom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Start_Page2 extends AppCompatActivity {

    DatabaseHelper dbHelper = new DatabaseHelper(Start_Page2.this);

    List<Player> players = new ArrayList<>();
    Deck GameDeck = new Deck();

    int CounterPlayer = 0;
    int DeckPressed = 0;

    int TrickCounter = 0;
    LinkedHashMap<Object, String> centerPile = GameDeck.getCenterPile();
    TextView Player1, Player2, Player3, Player4;
    TextView PlayerNameA, PlayerNameB, PlayerNameC, PlayerNameD;

    TextView Score;

    TextView PlayerTurn;
    TextView CentrePile;

    TextView Counter;

    TextView MainDeckPile;

    EditText CardName;

    Button Save;
    Button Exit;
    Button PlayCard;
    Button DeckButton;

    Button Draw;

    String checkLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page2);



        // Initialize UI elements
        Player1 = findViewById(R.id.Player1);
        Player2 = findViewById(R.id.Player2);
        Player3 = findViewById(R.id.Player3);
        Player4 = findViewById(R.id.Player4);

        PlayerNameA = findViewById(R.id.PlayerNameA);
        PlayerNameB = findViewById(R.id.PlayerNameB);
        PlayerNameC = findViewById(R.id.PlayerNameC);
        PlayerNameD = findViewById(R.id.PlayerNameD);

        PlayerTurn = findViewById(R.id.PlayerTurn);

        CentrePile = findViewById(R.id.CentrePile);

        CardName = findViewById(R.id.CardName);

        Save = findViewById(R.id.Save);

        Exit = findViewById(R.id.Exit);

        PlayCard = findViewById(R.id.Play);

        DeckButton = findViewById(R.id.DeckPile);

        MainDeckPile = findViewById(R.id.MainDeck);

        Draw = findViewById(R.id.Draw);

        Score = findViewById(R.id.Score);

        Counter = findViewById(R.id.Counter);

        Bundle bundle = getIntent().getExtras();
        checkLoad = bundle.getString("activity");
        if ("loaded".equals(checkLoad)) {
            // Define the columns you want to retrieve from the table
            String[] columns = { "players", "deck", "scores", "trick" };

            // Query the table and retrieve the data
            SQLiteDatabase database = dbHelper.getReadableDatabase();
            int RowCount = database.rawQuery("SELECT * from Game_Data", null).getCount();

                Cursor cursor = database.rawQuery("SELECT * FROM Game_Data WHERE id = " + RowCount, null);
                if (cursor.moveToFirst()) {

                    // Retrieve the JSON strings from the cursor
                    String playersJson = cursor.getString(1);
                    String deckJson = cursor.getString(2);
                    String scoresJson = cursor.getString(3);
                    TrickCounter = cursor.getInt(4);

                    // Use Gson to convert the JSON string to a LinkedHashMap
                    Type scoresType = new TypeToken<LinkedHashMap<String, Integer>>() {}.getType();
                    LinkedHashMap<String, Integer> scoresMap = new Gson().fromJson(scoresJson, scoresType);

                    // Use Gson to convert the JSON strings back to objects
                    List<Player> players2 = new Gson().fromJson(playersJson, new TypeToken<List<Player>>(){}.getType());
                    Deck deck = new Gson().fromJson(deckJson, Deck.class);

                    Log.d("Checking Scores : ", scoresMap.get("Player A").toString());
                    Log.d("Checking Players : ", players2.toString());
                    Log.d("Checking Deck : ", deck.toString());

                    // Do something with the loaded data
                    // For example, update your UI or perform any necessary operations
                    Toast.makeText(getApplicationContext(), "Data loaded successfully", Toast.LENGTH_SHORT).show();

                    Player.scores = scoresMap;
                    players = players2;
                    GameDeck = deck;

                    DeckPressed = 1;


                    System.out.println("Centre Pile : " + GameDeck.getCenterPile().toString());
                    System.out.println("Main Deck : " + GameDeck.getCards().toString());
                    for (Player player2 : players) {
                        System.out.println(player2.getName() + "'s Hand: " + player2.getHand());
                    }
                    Player.PlayerScores.displayScores();
                    System.out.println("Trick : " + TrickCounter);


                    CentrePile.setText(GameDeck.getCenterPile().toString());
                    Player1.setText(players.get(0).getHand().toString());
                    Player2.setText(players.get(1).getHand().toString());
                    Player3.setText(players.get(2).getHand().toString());
                    Player4.setText(players.get(3).getHand().toString());
                    PlayerNameA.setText(players.get(0).getName());
                    PlayerNameB.setText(players.get(1).getName());
                    PlayerNameC.setText(players.get(2).getName());
                    PlayerNameD.setText(players.get(3).getName());
                    Score.setText(Player.scores.toString());
                    MainDeckPile.setText(GameDeck.getCards().toString());
                    PlayerTurn.setText(players.get(CounterPlayer).getName().toString());
                    TrickCounter += 1;
                    Counter.setText("Trick  : " + TrickCounter);


                } else {
                    // No data found
                    Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();
                }

                // Close the cursor and database connection
                cursor.close();
                database.close();
        }



        // Set button click listeners
        Draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (Player player3 : players) {
                    // if yes the while loop will be set to false and it will break from this
                    // current for loop
                    // refer to the first while loop to understand better
                    if (player3.getHand().isEmpty()) {
                        Player.setScore(players);
                        Player.PlayerScores.displayScores();
                        Score.setText(Player.scores.toString());
                        Toast.makeText(Start_Page2.this, Player.scores.toString(), Toast.LENGTH_LONG).show();
                        System.out.println("The winner of the round is " + player3.getName());
                        PlayerTurn.setText(String.format("The Winner Is%s", player3.getName()));
                        System.out.println("The game ended with " + player3.getName()+ " finishing their cards in hand. !\n Congrats Player " + player3.getName());
                        Toast.makeText(Start_Page2.this, "The game ended with " + player3.getName()+ " finishing their cards in hand. !\n Congrats Player " + player3.getName(),Toast.LENGTH_LONG).show();
                        Tool.pause(5000);
                        Intent intent = new Intent(Start_Page2.this, MainActivity.class);
                        startActivity(intent);
                        // Handle exit button click event
                        bundle.clear();
                    }
                }



                if (DeckPressed == 1) {
                    char status = 'O';
                    if (!GameDeck.getCenterPile().isEmpty()) {
                        // refer to Play.java it will compare and return the appropriate character
                        // System.out.println(player.getHand());
                        status = Play.CardPlay(players.get(CounterPlayer).getHand(), GameDeck.getCenterPile());
                    } else if (GameDeck.getCenterPile().isEmpty()) {
                        status = 'O';
                    }

                    if (status == 'P' || status == 'S' || status == 'O') {
                        System.out.println("Cards are available!");
                        Toast.makeText(Start_Page2.this, "Cards are available!", Toast.LENGTH_SHORT).show();
                    } else if ((status == 'N') && (!GameDeck.getCards().isEmpty())) {
                        System.out.println("No Cards Available!\nCard(s) Will Be Drawn");
                        Toast.makeText(Start_Page2.this, "No Cards Available!\nCard(s) Will Be Drawn",Toast.LENGTH_SHORT).show();
                        // i put it under a while loop cause it will draw until you get a card
                        // as usual p for precedence an s for suits
                        while (true) {
                            if(!GameDeck.getCards().isEmpty()) {
                                Play.DrawMain(players.get(CounterPlayer).getHand(), GameDeck);

                                if (Play.CardPlay(players.get(CounterPlayer).getHand(), GameDeck.getCenterPile()) == 'S') {
                                    System.out.println("Cards were drawn");
                                    Toast.makeText(Start_Page2.this, "Cards were Drawn", Toast.LENGTH_SHORT).show();
                                    break;
                                } else if (Play.CardPlay(players.get(CounterPlayer).getHand(), GameDeck.getCenterPile()) == 'P') {
                                    System.out.println("Cards were drawn!");
                                    Toast.makeText(Start_Page2.this, "Cards were Drawn", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }else{
                                System.out.println("No more cards!");
                                Toast.makeText(Start_Page2.this, "No more cards", Toast.LENGTH_SHORT).show();
                                break;

                            }
                        }
                        // this is to show that cards have finished drawing
                        System.out.println("Card(s) Done Drawing");
                        Toast.makeText(Start_Page2.this, "Card(s) Done Drawing", Toast.LENGTH_SHORT).show();
                    } else if (GameDeck.getCards().isEmpty()) {
                        Toast.makeText(Start_Page2.this, "No Cards Available in the main Deck!\nNext Player's Turn", Toast.LENGTH_LONG).show();
                        System.out.println("No Cards Available in the main Deck!\nNext Player's Turn");
//                       GameDeck.AddCard(players.get(CounterPlayer).getName(), );
                        CounterPlayer = CounterPlayer + 1;
                        if (CounterPlayer >= 4) {
                            CounterPlayer = 0;
                            GameDeck.getCenterPile().clear();
                        }

                    }

                    System.out.println("Centre Pile : " + GameDeck.getCenterPile().toString());
                    System.out.println("Main Deck : " + GameDeck.getCards().toString());
                    for (Player player2 : players) {
                        System.out.println(player2.getName() + "'s Hand: " + player2.getHand());
                    }
                    Player.PlayerScores.displayScores();
                    System.out.println("Trick : " + TrickCounter);

                    CentrePile.setText(GameDeck.getCenterPile().toString());
                    Player1.setText(players.get(0).getHand().toString());
                    Player2.setText(players.get(1).getHand().toString());
                    Player3.setText(players.get(2).getHand().toString());
                    Player4.setText(players.get(3).getHand().toString());
                    PlayerNameA.setText(players.get(0).getName());
                    PlayerNameB.setText(players.get(1).getName());
                    PlayerNameC.setText(players.get(2).getName());
                    PlayerNameD.setText(players.get(3).getName());
                    Score.setText(Player.scores.toString());
                    MainDeckPile.setText(GameDeck.getCards().toString());
                    PlayerTurn.setText(players.get(CounterPlayer).getName().toString());
                    TrickCounter += 1;
                    Counter.setText("Trick  : " + TrickCounter);

                } else {
                    Toast.makeText(Start_Page2.this, "Please Press The Deck Button!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle save button click event
                if (CounterPlayer == 0) {
                    String playersjson = new Gson().toJson(players);
                    String deckjson = new Gson().toJson(GameDeck);
                    String scorejson = new Gson().toJson(Player.GetScore2());
                    boolean status = dbHelper.AddData(playersjson, deckjson, scorejson, TrickCounter);
                    if (status) {
                        Toast.makeText(Start_Page2.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Start_Page2.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Start_Page2.this, "You can only save when its the first player's turn!", Toast.LENGTH_LONG).show();
                }
            }
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Start_Page2.this, MainActivity.class);
                startActivity(intent);
                // Handle exit button click event
                bundle.clear();
            }
        });

        DeckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DeckPressed == 0) {
                    // shuffle cards
                    GameDeck.shuffle();
                    // get first card in the center pile
                    // To get something from a map, you have to use its identifier. in this case,
                    // ive assigned "*" to be the identifier for the first card in the deck
                    String centerCard = centerPile.get("*");
                    Tool.clearScreen();
                    System.out.println("Center Pile: " + GameDeck.getCenterPile());
                    Tool.pause(1000);
                    // n is the value to determine the first player, the list will be rotated
                    // according to n
                    int n;
                    // this block of code matches the ranks to determine the first player
                    if (centerCard.contains("K") || centerCard.contains("A") || centerCard.contains("9")
                            || centerCard.contains("5")) {
                        n = 0;
                        System.out.println("First Player will be Player A!");
                        Tool.pause(1000);
                    } else if (centerCard.contains("2") || centerCard.contains("6") || centerCard.contains("X")) {
                        n = 3;
                        System.out.println("First Player will be Player B!");
                        Tool.pause(1000);
                    } else if (centerCard.contains("3") || centerCard.contains("7") || centerCard.contains("J")) {
                        n = 2;
                        System.out.println("First Player will be Player C!");
                        Tool.pause(1000);
                    } else if (centerCard.contains("4") || centerCard.contains("8") || centerCard.contains("Q")) {
                        n = 1;
                        System.out.println("First Player will be Player D!");
                        Tool.pause(1000);
                    } else {
                        n = (int) (Math.random() * 3);
                    }

                    // create list of players based on int n
                    Player.CreatePlayersList(n, players);
                    // deal cards to players
                    GameDeck.dealCards(players);
                    Tool.clearScreen();
                    // Handle deck pile button click event


                    System.out.println("Centre Pile : " + GameDeck.getCenterPile().toString());
                    System.out.println("Main Deck : " + GameDeck.getCards().toString());
                    for (Player player2 : players) {
                        System.out.println(player2.getName() + "'s Hand: " + player2.getHand());
                    }
                    Player.PlayerScores.displayScores();
                    System.out.println("Trick : " + TrickCounter);

                    MainDeckPile.setText(GameDeck.getCards().toString());
                    CentrePile.setText(GameDeck.getCenterPile().toString());
                    Player1.setText(players.get(0).getHand().toString());
                    Player2.setText(players.get(1).getHand().toString());
                    Player3.setText(players.get(2).getHand().toString());
                    Player4.setText(players.get(3).getHand().toString());
                    PlayerNameA.setText(players.get(0).getName());
                    PlayerNameB.setText(players.get(1).getName());
                    PlayerNameC.setText(players.get(2).getName());
                    PlayerNameD.setText(players.get(3).getName());
                    PlayerTurn.setText(players.get(0).getName().toString());
                    Score.setText(Player.scores.toString());
                    PlayerTurn.setText(players.get(CounterPlayer).getName().toString());
                    TrickCounter += 1;
                    Counter.setText("Trick  : " + TrickCounter);

                    DeckPressed = 1;
                    Toast.makeText(Start_Page2.this, "Cards dealt!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Start_Page2.this, "Already pressed the deck button!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        PlayCard.setOnClickListener(new View.OnClickListener() {

            char status;

            @Override
            public void onClick(View view) {
                if (DeckPressed == 1) {

                    status = 'O';

                    for (Player player3 : players) {
                        // if yes the while loop will be set to false and it will break from this
                        // current for loop
                        // refer to the first while loop to understand better
                        if (player3.getHand().isEmpty()) {
                            Player.setScore(players);

                            Score.setText(Player.scores.toString());
                            Toast.makeText(Start_Page2.this, Player.scores.toString(), Toast.LENGTH_LONG).show();
                            System.out.println("The winner of the round is " + player3.getName());
                            PlayerTurn.setText(String.format("The Winner Is%s", player3.getName()));
                            System.out.println("The game ended with " + player3.getName()+ " finishing their cards in hand. !\n Congrats Player " + player3.getName());
                            Toast.makeText(Start_Page2.this, "The game ended with " + player3.getName()+ " finishing their cards in hand. !\n Congrats Player " + player3.getName(),Toast.LENGTH_LONG).show();
                            Player.PlayerScores.displayScores();
                            Tool.pause(5000);
                            Intent intent = new Intent(Start_Page2.this, MainActivity.class);
                            startActivity(intent);
                            // Handle exit button click event
                            bundle.clear();
                        }
                    }

                    if (CounterPlayer >= 4) {
                        CounterPlayer = 0;
                        GameDeck.getCenterPile().clear();
                    }

                    System.out.println("Centre Pile : " + GameDeck.getCenterPile().toString());
                    System.out.println("Main Deck : " + GameDeck.getCards().toString());
                    for (Player player2 : players) {
                        System.out.println(player2.getName() + "'s Hand: " + player2.getHand());
                    }
                    Player.PlayerScores.displayScores();
                    System.out.println("Trick : " + TrickCounter);

                    PlayerTurn.setText(players.get(CounterPlayer).getName().toString());
                    MainDeckPile.setText(GameDeck.getCards().toString());
                    CentrePile.setText(GameDeck.getCenterPile().toString());
                    Player1.setText(players.get(0).getHand().toString());
                    Player2.setText(players.get(1).getHand().toString());
                    Player3.setText(players.get(2).getHand().toString());
                    Player4.setText(players.get(3).getHand().toString());
                    PlayerNameA.setText(players.get(0).getName());
                    PlayerNameB.setText(players.get(1).getName());
                    PlayerNameC.setText(players.get(2).getName());
                    PlayerNameD.setText(players.get(3).getName());
                    PlayerTurn.setText(players.get(CounterPlayer).getName().toString());
                    Score.setText(Player.scores.toString());
                    Counter.setText("Trick  : " + TrickCounter);

                    if (!GameDeck.getCenterPile().isEmpty()) {
                        // refer to Play.java it will compare and return the appropriate character
                        // System.out.println(player.getHand());
                        status = Play.CardPlay(players.get(CounterPlayer).getHand(), GameDeck.getCenterPile());
                    } else if (GameDeck.getCenterPile().isEmpty()) {
                        status = 'O';
                    }

                    if (status == 'P' || status == 'S' || status == 'O') {
                        System.out.println("Cards are available!");
                        Toast.makeText(Start_Page2.this, "Cards are available!", Toast.LENGTH_SHORT).show();
                    } else if ((status == 'N') && (!GameDeck.getCards().isEmpty())) {
                        System.out.println("No Cards Available!\nCard(s) Will Be Drawn");
                        Toast.makeText(Start_Page2.this, "No Cards Available!\nCard(s) Will Be Drawn", Toast.LENGTH_SHORT).show();
                        // i put it under a while loop cause it will draw until you get a card
                        // as usual p for precedence an s for suits
                        while (true) {
                            if(!GameDeck.getCards().isEmpty()) {
                                Play.DrawMain(players.get(CounterPlayer).getHand(), GameDeck);

                                if (Play.CardPlay(players.get(CounterPlayer).getHand(), GameDeck.getCenterPile()) == 'S') {
                                    System.out.println("Cards were drawn");
                                    Toast.makeText(Start_Page2.this, "Cards were Drawn", Toast.LENGTH_SHORT).show();
                                    break;
                                } else if (Play.CardPlay(players.get(CounterPlayer).getHand(), GameDeck.getCenterPile()) == 'P') {
                                    System.out.println("Cards were drawn!");
                                    Toast.makeText(Start_Page2.this, "Cards were Drawn", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }else{
                                System.out.println("No more cards!");
                                Toast.makeText(Start_Page2.this, "No more cards", Toast.LENGTH_SHORT).show();
                                break;

                            }
                        }
                        // this is to show that cards have finished drawing
                        System.out.println("Card(s) Done Drawing");
                        Toast.makeText(Start_Page2.this, "Card(s) Done Drawing", Toast.LENGTH_SHORT).show();
                    } else if (GameDeck.getCards().isEmpty()) {
                        Toast.makeText(Start_Page2.this, "No Cards Available in the main Deck!\nNext Player's Turn", Toast.LENGTH_LONG).show();
                        System.out.println("No Cards Available in the main Deck!\nNext Player's Turn");
                        CounterPlayer = CounterPlayer + 1;
                        if (CounterPlayer >= 4) {
                            CounterPlayer = 0;
                            GameDeck.getCenterPile().clear();
                        }
                    }

                    // Handle play card button click event
                    String PlayedCard = CardName.getText().toString();
                    System.out.println(players.get(CounterPlayer).getHand());

                    if (Play.CardContains(PlayedCard, players.get(CounterPlayer).getHand())) {
                        // this is to see whether the card they played is equal in suits or precedence
                        if (!GameDeck.getCenterPile().isEmpty()) {
                            status = Play.CardPlay(PlayedCard, GameDeck.getCenterPile());
                        } else if (GameDeck.getCenterPile().isEmpty()) {
                            players.get(CounterPlayer).removeCard(PlayedCard);
                            GameDeck.AddCard(players.get(CounterPlayer).getName(), PlayedCard);
                        }

                        if (status == 'S') {
                            // int HandIndex = Play.IndexOf(player.getHand(), PlayedCard);
                            players.get(CounterPlayer).removeCard(PlayedCard);
                            GameDeck.AddCard(players.get(CounterPlayer).getName(), PlayedCard);

                        } else if (status == 'P') {
                            // int HandIndex = Play.IndexOf(player.getHand(), PlayedCard);
                            players.get(CounterPlayer).removeCard(PlayedCard);
                            GameDeck.AddCard(players.get(CounterPlayer).getName(), PlayedCard);

                        } else if (status == 'O') {
                            // int HandIndex = Play.IndexOf(player.getHand(), PlayedCard);
                            players.get(CounterPlayer).removeCard(PlayedCard);
                            GameDeck.AddCard(players.get(CounterPlayer).getName(), PlayedCard);

                        } else if (status == 'N') {
                            System.out.println("Please Choose a Right Card!");
                            Toast.makeText(Start_Page2.this, "Please choose a right card", Toast.LENGTH_SHORT).show();
                            CounterPlayer = CounterPlayer - 1;
                        }
                    } else {

                        System.out.println("Does not exist in your deck");
                        CounterPlayer = CounterPlayer - 1;
                    }



                    int FirstPlayer, HighestCard;

                    if (CounterPlayer == 3) {
                        FirstPlayer = Play.HighestPlayerIndex(GameDeck.getCenterPile()).get(0);
                        HighestCard = Play.HighestPlayerIndex(GameDeck.getCenterPile()).get(1);
                        Player.PlayerScores.displayScores();

                        System.out.println("First Player For The Next Round : " + players.get(FirstPlayer).getName());
                        System.out.println("Previous Round Center Pile : " + GameDeck.getCenterPile() + "\n");

                        Toast.makeText(Start_Page2.this,
                                "First Player For The Next Round : " + players.get(FirstPlayer).getName(),
                                Toast.LENGTH_SHORT).show();
                        Toast.makeText(Start_Page2.this,
                                        "Previous Round Center Pile : " + GameDeck.getCenterPile() + "\n", Toast.LENGTH_SHORT)
                                .show();
                        players = Tool.ReArrange(players, FirstPlayer);
                        GameDeck.getCenterPile().clear();
                        TrickCounter += 1;
                        Counter.setText("Trick  :" + TrickCounter);
                    }

                    System.out.println("Centre Pile : " + GameDeck.getCenterPile().toString());
                    System.out.println("Main Deck : " + GameDeck.getCards().toString());
                    for (Player player2 : players) {
                        System.out.println(player2.getName() + "'s Hand: " + player2.getHand());
                    }
                    Player.PlayerScores.displayScores();
                    System.out.println("Trick : " + TrickCounter);

                    MainDeckPile.setText(GameDeck.getCards().toString());
                    CentrePile.setText(GameDeck.getCenterPile().toString());
                    Player1.setText(players.get(0).getHand().toString());
                    Player2.setText(players.get(1).getHand().toString());
                    Player3.setText(players.get(2).getHand().toString());
                    Player4.setText(players.get(3).getHand().toString());
                    PlayerNameA.setText(players.get(0).getName());
                    PlayerNameB.setText(players.get(1).getName());
                    PlayerNameC.setText(players.get(2).getName());
                    PlayerNameD.setText(players.get(3).getName());
                    Score.setText(Player.scores.toString());
                    Counter.setText("Trick  : " + TrickCounter);

                    CounterPlayer = CounterPlayer + 1;
                    if (CounterPlayer >= 4) {
                        CounterPlayer = 0;
                    }
                    PlayerTurn.setText(players.get(CounterPlayer).getName().toString());

                } else {
                    Toast.makeText(Start_Page2.this, "Please Press The Deck Button", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    //
    // public void AddData(String playerjson, String deckjson, String scoresjson){
    // Boolean DataEntry = dbHelper.AddData(playerjson,deckjson,scoresjson);
    // if(DataEntry){
    // Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    // }else{
    // Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
    // }
    //
    // }

    @Override
    protected void onStart() {
        super.onStart();
    }

}