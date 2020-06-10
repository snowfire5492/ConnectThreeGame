package com.example.connectthreegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private boolean afroTurn = true;

    public void placeGamePiece(View view) {
        ImageView counter = (ImageView) view;   // just get an image view of the view to access resources


        counter.getTag();

        Log.i("tag", counter.getTag().toString());


        counter.setTranslationY(-1000);         // place view off of screen

        if(afroTurn){
            counter.setImageResource(R.drawable.afro_player);  // set game piece
            afroTurn = false;
        } else {
            counter.setImageResource(R.drawable.anime_player);  // set game piece
            afroTurn = true;
        }


        counter.animate().translationYBy(1000).rotation(3600).setDuration(1000);    // move into place
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}