package com.example.bodeng.boplayhouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class EnterNamesActivity extends AppCompatActivity {

    TableLayout nameEntryTable;
    int numPlayers;
    GameManager gm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_names);
        nameEntryTable = (TableLayout)(findViewById(R.id.names));
        Bundle extras = getIntent().getExtras();
        numPlayers = Integer.parseInt(extras.getString("NumPlays"));
        TableRow row1 = this.findViewById(R.id.row1);
        TableRow row2 = this.findViewById(R.id.row2);
        TableRow row3 = this.findViewById(R.id.row3);
        TableRow row4 = this.findViewById(R.id.row4);
        gm = (GameManager) extras.getSerializable("GameState");
        if (numPlayers < 4){
            row4.setVisibility(View.GONE);
        }
        if (numPlayers < 3){
            row3.setVisibility(View.GONE);
        }
        if (numPlayers < 2){
            row2.setVisibility(View.GONE);
        }
    }

    public void onClick(View view) {
        Intent instructions = new Intent(this, com.example.bodeng.boplayhouse.InstructionsViewer.class);
        if (numPlayers == 4){
            String[] names = {((TextView)(findViewById(R.id.name1Enter))).getText().toString(), ((TextView)(findViewById(R.id.name2Enter))).getText().toString()
            , ((TextView)(findViewById(R.id.name3Enter))).getText().toString(), ((TextView)(findViewById(R.id.name4Enter))).getText().toString()};
            gm.setPlayerNames(names);
        }
        if (numPlayers == 3){
            String[] names = {((TextView)(findViewById(R.id.name1Enter))).getText().toString(), ((TextView)(findViewById(R.id.name2Enter))).getText().toString()
                    , ((TextView)(findViewById(R.id.name3Enter))).getText().toString()};
            gm.setPlayerNames(names);
        }
        if (numPlayers == 2){
            String[] names = {((TextView)(findViewById(R.id.name1Enter))).getText().toString(), ((TextView)(findViewById(R.id.name2Enter))).getText().toString()};
            gm.setPlayerNames(names);
        }
        if (numPlayers == 1){
            String[] names = {((TextView)(findViewById(R.id.name1Enter))).getText().toString()};
            gm.setPlayerNames(names);
        }
        instructions.putExtra("GameState", gm);
        startActivity(instructions);
    }
}
