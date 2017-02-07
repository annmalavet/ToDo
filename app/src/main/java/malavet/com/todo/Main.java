package malavet.com.todo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

;

public class Main extends AppCompatActivity implements AdapterView.OnItemClickListener {
    DBHelp dbHelper;
    ListView list;
    SQLiteDatabase db;
    ArrayAdapter<String> arrAdapt;
    ArrayList<String> aList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list = (ListView) findViewById(R.id.list);

        dbHelper = new DBHelp(this.getBaseContext());
        db = dbHelper.getWritableDatabase();
        aList = new ArrayList<String>();
        aList = dbHelper.something();
        CursorAdapt cA = new CursorAdapt(this, dbHelper.getC(), 0);
        list.setAdapter(cA);
        list.setOnItemClickListener(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddItem.class);
                startActivity(i);
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
    @Override
    public void onResume() {
        super.onResume();
        dbHelper = new DBHelp(this.getBaseContext());
        aList = dbHelper.something();
        CursorAdapt cA = new CursorAdapt(this, dbHelper.getC(), 0);
        list.setAdapter(cA);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String s = (String) aList.get(i);
        Intent j = new Intent(view.getContext(), EditItem.class);
        Bundle b = new Bundle();
        b.putString("s", s);
        j.putExtras(b);
        startActivity(j);
    }


    @Override
    protected void onStop(){
        super.onStop();

    }

}
