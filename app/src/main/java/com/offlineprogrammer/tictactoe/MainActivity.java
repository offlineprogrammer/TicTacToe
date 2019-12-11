package com.offlineprogrammer.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // 0: X; 1: O; 2: Empty

    Integer[] gameState = {2,2,2,2,2,2,2,2,2};

    int activePlayer = 0;

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playTic(View view) {
        ImageView counter = (ImageView) view;
        String winner = "";
        Button restartButton =  findViewById(R.id.restartButton);
        TextView winnerTextView =  findViewById(R.id.winnerTextView);
        int selectedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[selectedCounter] == 2 && gameActive) {


            counter.setTranslationY(-1500);
            gameState[selectedCounter] = activePlayer;

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.cross);

                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.circle);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    // winner



                    if (activePlayer == 1) {
                        winner = " X ";

                    } else {
                        winner = " O ";

                    }

                    gameActive = false;







                    winnerTextView.setText("The winner is " + winner);
                    restartButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);


                }
            }
        }

        if (Arrays.asList(gameState).indexOf(2) == -1 && gameActive) {

            winnerTextView.setText("No Winner " + Arrays.asList(gameState).indexOf(2));
            restartButton.setVisibility(View.VISIBLE);
            winnerTextView.setVisibility(View.VISIBLE);

        }
    }

    public void restartGame(View view) {

        gameActive = true;
        activePlayer = 0;

        for (int i=0; i<gameState.length; i++){
            gameState[i] = 2;

        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            child.setImageResource(0);
        }

        Button restartButton = (Button) findViewById(R.id.restartButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        winnerTextView.setText("");
        restartButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);



    }
}
