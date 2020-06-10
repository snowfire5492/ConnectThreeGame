package com.example.connectthreegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean afroTurn = true;
    private String[] gameState = {"none", "none", "none"
                                , "none", "none", "none"
                                , "none", "none", "none"};

    public void placeGamePiece(View view) {
        ImageView counter = (ImageView) view;   // just get an image view of the view to access resources

        if( gameState[Integer.parseInt(counter.getTag().toString())]== "none") {

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
                Toast.makeText(this, "Afro Warrior Wins!!!", Toast.LENGTH_SHORT).show();
            } else if( playerHasWon() && !afroTurn ) {
                Toast.makeText(this, "Asian Warrior Wins!!!", Toast.LENGTH_SHORT).show();
            }

            // next players turn
            if(afroTurn) afroTurn = false;
            else afroTurn = true;

        } else {
            Toast.makeText(this, "Try Different Spot", Toast.LENGTH_SHORT).show();
        }

        if (boardIsFull() && !playerHasWon()) {
            Toast.makeText(this, "Tie Game", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean playerHasWon() {

        // horizontal win
        if( gameState[0] == gameState[1] && gameState[0] == gameState[2] && gameState[0] != "none" ||
                gameState[3] == gameState[4] && gameState[3] == gameState[5] && gameState[3] != "none"||
                gameState[6] == gameState[7] && gameState[6] == gameState[8] && gameState[6] != "none") {
            return true;
        }

        // vertical win
        if( gameState[0] == gameState[3] && gameState[0] == gameState[6] && gameState[0] != "none" ||
                gameState[1] == gameState[4] && gameState[1] == gameState[7] && gameState[1] != "none"||
                gameState[2] == gameState[5] && gameState[2] == gameState[8] && gameState[2] != "none") {
            return true;
        }

        // diagonal win
        if( gameState[0] == gameState[4] && gameState[0] == gameState[8] && gameState[0] != "none" ||
                gameState[6] == gameState[4] && gameState[6] == gameState[2] && gameState[6] != "none") {
            return true;
        }

        return false;
    }

    private boolean boardIsFull() {

        for (String el: gameState) {
            if (el == "none") {
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