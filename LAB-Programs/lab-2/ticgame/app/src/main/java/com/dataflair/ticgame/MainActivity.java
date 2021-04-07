package com.dataflair.ticgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private int grid_size;
    TableLayout gameBoard;
    TextView txt_turn, error_msg;
    char [][] my_board;
    char turn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid_size = Integer.parseInt(getString(R.string.size_of_board));
        my_board = new char [grid_size][grid_size];
        gameBoard = (TableLayout) findViewById(R.id.mainBoard);
        txt_turn = (TextView) findViewById(R.id.turn);
        error_msg = (TextView) findViewById(R.id.error);

        resetBoard();
        txt_turn.setText("Turn: "+turn);

        for(int i = 0; i< gameBoard.getChildCount(); i++){
            TableRow row = (TableRow) gameBoard.getChildAt(i);
            for(int j = 0; j<row.getChildCount(); j++){
                Button tv = (Button) row.getChildAt(j);
                tv.setText("-");
                tv.setOnClickListener(Move(i, j, tv));
            }
        }

        Button reset_btn = (Button) findViewById(R.id.reset);
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent current = getIntent();
                finish();
                startActivity(current);
            }
        });


        Toast.makeText(this, "Player X: Turn", Toast.LENGTH_SHORT).show();
    }

    protected void resetBoard(){
        turn = 'X';
        for(int i = 0; i< grid_size; i++){
            for(int j = 0; j< grid_size; j++){
                my_board[i][j] = ' ';
            }
        }
    }

    protected int gameStatus(){

        //0 Continue
        //1 X Wins
        //2 O Wins
        //-1 Draw

        int rowX = 0, colX = 0, rowO = 0, colO = 0;
        for(int i = 0; i< grid_size; i++){
            if(check_Row_Equality(i,'X'))
                return 1;
            if(check_Column_Equality(i, 'X'))
                return 1;
            if(check_Row_Equality(i,'O'))
                return 2;
            if(check_Column_Equality(i,'O'))
                return 2;
            if(check_Diagonal('X'))
                return 1;
            if(check_Diagonal('O'))
                return 2;
        }

        boolean boardFull = true;
        for(int i = 0; i< grid_size; i++){
            for(int j = 0; j< grid_size; j++){
                if(my_board[i][j]==' ')
                    boardFull = false;
            }
        }
        if(boardFull)
            return -1;
        else return 0;
    }

    protected boolean check_Diagonal(char player){
        int count_Equal1 = 0,count_Equal2 = 0;
        for(int i = 0; i< grid_size; i++)
            if(my_board[i][i]==player)
                count_Equal1++;
        for(int i = 0; i< grid_size; i++)
            if(my_board[i][grid_size -1-i]==player)
                count_Equal2++;
        if(count_Equal1== grid_size || count_Equal2== grid_size)
            return true;
        else return false;
    }

    protected boolean check_Row_Equality(int r, char player){
        int count_Equal=0;
        for(int i = 0; i< grid_size; i++){
            if(my_board[r][i]==player)
                count_Equal++;
        }

        if(count_Equal== grid_size)
            return true;
        else
            return false;
    }

    protected boolean check_Column_Equality(int c, char player){
        int count_Equal=0;
        for(int i = 0; i< grid_size; i++){
            if(my_board[i][c]==player)
                count_Equal++;
        }

        if(count_Equal== grid_size)
            return true;
        else
            return false;
    }

    protected boolean Cell_Set(int r, int c){
        return !(my_board[r][c]==' ');
    }

    protected void stopMatch(){
        for(int i = 0; i< gameBoard.getChildCount(); i++){
            TableRow row = (TableRow) gameBoard.getChildAt(i);
            for(int j = 0; j<row.getChildCount(); j++){
                TextView tv = (TextView) row.getChildAt(j);
                tv.setOnClickListener(null);
            }
        }
    }

    private void ToastFlash(String msg){
        Toast.makeText(this, msg, -1).show();
    }

    View.OnClickListener Move(final int r, final int c, final Button tv){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                error_msg.setText("");

                if(!Cell_Set(r,c)) {
                    my_board[r][c] = turn;
                    if (turn == 'X') {
                        tv.setText(R.string.X);
                        turn = 'O';
                        ToastFlash("Player O: Turn");
                    } else if (turn == 'O') {
                        tv.setText(R.string.O);
                        turn = 'X';
                        ToastFlash("Player X: Turn");
                    }
                    if (gameStatus() == 0) {
                        txt_turn.setText("Turn: Player " + turn);
                    }
                    else if(gameStatus() == -1){
                        txt_turn.setText("This is a Draw match");
                        ToastFlash("This is a Draw match");
                        stopMatch();
                    }
                    else{
                        txt_turn.setText("Player " + turn + " Loses! \n" + "Player " + (turn == 'X' ? "O" : "X") + " Wins!");
                        ToastFlash("Player " + (turn == 'X' ? "O" : "X") + " Wins!");
                        stopMatch();
                    }
                }
                else{
                    error_msg.setText(" Select an Empty Cell");
                }
            }
        };
    }
}