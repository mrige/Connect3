package com.trillogeniusss.connect3;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    int turn = 0;
    int [] board = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int [][] winningPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void taptap(View view){
	ImageView counter1 = (ImageView)view;
	int pos = Integer.parseInt(counter1.getTag().toString());
	if(board[pos]== -1){
	    board[pos] = turn;
	    counter1.setTranslationY(-1000f);
	    if(turn == 0){
		counter1.setImageResource(R.drawable.single);
	    }else{
		counter1.setImageResource(R.drawable.inner);
	    }
	    counter1.animate().translationYBy(1000f).setDuration(30);
	    boolean isWinner = checkWinner(turn);

	    if(turn == 0){
		turn =1;
	    }else{
		turn =0;
	    }

	}else{
	    Toast.makeText(getApplicationContext(), "Position Taken : Try a different Location.", Toast.LENGTH_LONG ).show();
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
		//Toast.makeText(getApplicationContext(), "Player " + (turn+1) + " wins", Toast.LENGTH_LONG ).show();
		winner = true;
		break;
	    }
	}
	return winner;
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
