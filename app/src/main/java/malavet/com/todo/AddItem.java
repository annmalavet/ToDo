package malavet.com.todo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddItem extends AppCompatActivity implements View.OnClickListener {
    public static  String p;
    public static  String q;
    public static String a;
    DBHelp dbHelper;
    EditText detail;
    SQLiteDatabase db;
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(malavet.com.todo.R.layout.add_item);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dbHelper = new DBHelp(this.getBaseContext());
        Button add = (Button) findViewById(malavet.com.todo.R.id.add_item);
        name = (EditText) findViewById(malavet.com.todo.R.id.item_name);
        detail = (EditText) findViewById(R.id.item_detail);

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dbHelper.openDB(db);
                q = detail.getText().toString();// detail
                p = name.getText().toString();// ite
                if (p.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please enter a list item before you save.", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper.insertItem(q, p);
                    //Toast.makeText(getApplicationContext(), p + " " + q, Toast.LENGTH_LONG).show();
                    dbHelper.close();
                    Intent i = new Intent(v.getContext(), Main.class);
                    startActivity(i);
                }

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(malavet.com.todo.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == malavet.com.todo.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }
}