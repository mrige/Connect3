package com.trillogeniusss.connect3;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
    int turn = 0;// 0 is for player 1 and 1 is for player 2
    int [] board = {-1,-1,-1,-1,-1,-1,-1,-1,-1};//keeps track of the used position on the grid, set to a default of -1
    int [][] winningPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};// array of possible winning positions on the grid
    boolean isActive = true;// checks if the boris still active
    int plays = 0;//checks how many moves have been made by the players

    public void taptap(View view){
	ImageView counter1 = (ImageView)view;
	int pos = Integer.parseInt(counter1.getTag().toString());

	if(board[pos]== -1 && isActive){

	    board[pos] = turn;
	    counter1.setTranslationY(-1000f);

	    if(turn == 0){
		counter1.setImageResource(R.drawable.single);
	    }else{
		counter1.setImageResource(R.drawable.inner);
	    }

	    counter1.animate().translationYBy(1000f).setDuration(30);

	    checkWinner(turn);

	    if(turn == 0){
		turn =1;
	    }else{
		turn =0;
	    }
	}
    }

    public boolean checkWinner(int turn){
	int count = 0;
	boolean winner = false;

	for (int i = 0; i < winningPos.length; i++){
	    for(int j = 0; j< winningPos[i].length; j++) {
		int val = winningPos[i][j];
		if(board[val]== turn){
		    System.out.println("Checking next position");
		    count++;
		}
		else {
		    System.out.println("Checking next possible  winning positions ");
		    break;
		}
	    }

	    if(count == 3){
		LinearLayout winnerMsg = (LinearLayout)findViewById(R.id.playagainlayout);
		TextView winnerText = (TextView)findViewById(R.id.winnerstext);
		winnerText.setText("Player " + (turn+1) + " Wins!!!!");
		winnerMsg.setVisibility(View.VISIBLE);
		winner = true;
		isActive = false;
		break;
	    }
	    else{
		count = 0;
	    }
	}
	plays++;
	if(plays==9){
	    LinearLayout winnerMsg = (LinearLayout)findViewById(R.id.playagainlayout);
	    TextView winnerText = (TextView)findViewById(R.id.winnerstext);
	    winnerText.setText("Its A Tie");
	    winnerMsg.setVisibility(View.VISIBLE);
	    winner = false;
	    isActive = false;
	}
	return winner;
    }

    public void playAgain(View view){

	LinearLayout winnerMsg = (LinearLayout)findViewById(R.id.playagainlayout);
	winnerMsg.setVisibility(View.INVISIBLE);
	turn = 0;
	isActive = true;
	plays = 0;
	for(int j =0 ; j<board.length; j++) {
	    board[j] = -1;
	}

	GridLayout grid = (GridLayout)findViewById(R.id.grid );

	for (int i =0; i < grid.getChildCount();i++){
	    ((ImageView) grid.getChildAt(i)).setImageResource(0);
	}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


	FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
	fab.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View view) {
		Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
		    .setAction("Action", null).show();
	    }
	});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.menu_main, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
	int id = item.getItemId();

	//noinspection SimplifiableIfStatement
	if (id == R.id.action_settings) {
	    return true;
	}

	return super.onOptionsItemSelected(item);
    }
}
