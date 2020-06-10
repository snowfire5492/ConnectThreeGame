package com.example.connectthreegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//import android.widget.GridLayout;
import androidx.gridlayout.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean afroTurn = true, gameActive = true;
    private String[] gameState = {"empty", "empty", "empty"
                                , "empty", "empty", "empty"
                                , "empty", "empty", "empty"};
    
    private int[][] winningStates = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void placeGamePiece(View view) {
        ImageView counter = (ImageView) view;   // just get an image view of the view to access resources

        if( gameState[Integer.parseInt(counter.getTag().toString())]== "empty" && gameActive ) {

            counter.setTranslationY(-1000);         // place view off of screen

            if(afroTurn){
                counter.setImageResource(R.drawable.afro_player);  // set game piece
                gameState[Integer.parseInt(counter.getTag().toString())] = "afro";
            } else {
                counter.setImageResource(R.drawable.anime_player);  // set game piece
                gameState[Integer.parseInt(counter.getTag().toString())] = "anime";
            }

            counter.animate().translationYBy(1000).rotation(3600).setDuration(1000);    // move into place

            if( playerHasWon() && afroTurn ) {
                showWinner("Afro");
            } else if( playerHasWon() && !afroTurn ) {
                showWinner("Asian");
            }

            // next players turn
            if(afroTurn) afroTurn = false;
            else afroTurn = true;

        } else if (gameActive) {
            Toast.makeText(this, "Try Different Spot", Toast.LENGTH_SHORT).show();
        }

        if (boardIsFull() && !playerHasWon() && gameActive) {
            Toast.makeText(this, "Tie Game", Toast.LENGTH_SHORT).show();
            gameActive = false;
        }

        if (!gameActive) {
            Toast.makeText(this, "Please Play Again", Toast.LENGTH_SHORT).show();
        }

    }

    private void showWinner(String winner){
        TextView textView = (TextView) findViewById(R.id.textViewWinner);
        textView.setTranslationX(-1500);
        textView.setText(winner + " Warrior Wins!!!");
        textView.animate().translationXBy(1500).alphaBy(1).setDuration(2000);
    }

    public void playAgain(View view) {
        Button replayBtn = (Button) findViewById(R.id.buttonPlayAgain);
        replayBtn.setClickable(false);
        replayBtn.setAlpha(0);

        findViewById(R.id.textViewWinner).setAlpha(0);
        gameActive = true;
        afroTurn = true;

        resetGameState();

        resetGameBoard();
    }

    private void resetGameBoard() {
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i< gridLayout.getChildCount(); ++i) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            //child.setImageResource(0);
            child.setImageDrawable(null);
        }
    }

    private void resetGameState() {
        for (int i = 0; i < gameState.length; ++i){
            gameState[i] = "empty";
        }
    }

    private boolean playerHasWon() {

        for (int[] winState: winningStates) {
            if(gameState[winState[0]] == gameState[winState[1]] 
                    && gameState[winState[1]] == gameState[winState[2]] 
                    && gameState[winState[0]] != "empty") {

                gameActive = false;

                Button playAgainBtn = (Button) findViewById(R.id.buttonPlayAgain);
                playAgainBtn.setTranslationY(1000);
                playAgainBtn.animate().translationYBy(-1000).alphaBy(1).setDuration(3000);
                playAgainBtn.setClickable(true);
                return true;
            }
        }

        return false;
    }

    private boolean boardIsFull() {

        for (String el: gameState) {
            if (el == "empty") {
                return false;
            }
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}