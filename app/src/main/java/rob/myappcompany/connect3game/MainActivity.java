package rob.myappcompany.connect3game;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    // 0: yellow, 1: red, 2: empty
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 0;

    // ゲームの状態：勝者がいない場合: true
    boolean gameActive = true;

    public void dropIn (View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        // 枠が空の場合、かつまだ勝者がいない場合 に動作する処理
        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;

            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            String winner = "";
            
            for (int[] winningPosition : winningPosition) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    // Some has won!

                    gameActive = false;


                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText("✨✨" + winner + " has won! ✨✨");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }

            if(gameState[0]!=2 && gameState[1]!=2 && gameState[2]!=2 && gameState[3]!=2 && gameState[4]!=2 && gameState[5]!=2 && gameState[6]!=2 && gameState[7]!=2 && gameState[8]!=2) {

                gameActive = false;

                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                winnerTextView.setText( "Draw!");

                playAgainButton.setVisibility(View.VISIBLE);

                winnerTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void playAgain (View view) {
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for (int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }

        activePlayer = 0;

        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
