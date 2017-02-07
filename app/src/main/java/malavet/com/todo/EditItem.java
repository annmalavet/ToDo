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
import java.util.ArrayList;


public class EditItem extends AppCompatActivity implements View.OnClickListener {
    public static  String p;
    public static  String q;
    public static  String a;
    DBHelp dbHelper;
    EditText detail;
    SQLiteDatabase db;
    EditText name;
    String index;
    String row;
    ArrayList<String> s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.edit_item);
        dbHelper = new DBHelp(this.getBaseContext());
        Button add = (Button) findViewById(R.id.save_item);
        Button delete = (Button) findViewById(R.id.delete_item);
        name = (EditText) findViewById(R.id.item_name);
        detail = (EditText) findViewById(R.id.item_detail);
        Bundle b = getIntent().getExtras();
        index = b.getString("s");
        dbHelper.openDB(db);
          s =  dbHelper.getRowArray(index);
        String row = s.get(0);
        String o = s.get(2);
        detail.setText(o);
        String c =  s.get(1);
        name.setText( c);
         delete.setOnClickListener(this);
        add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId()) {
            case (R.id.save_item):
                row = s.get(0);
                q = detail.getText().toString();
                p = name.getText().toString();
                if (p.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please enter an item before you save.", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper.openDB(db);
                    dbHelper.updateRow(p, q, row);
                    Toast.makeText(getApplicationContext(), p + " " + q + row, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(v.getContext(), Main.class);
                    startActivity(i);
                }
                break;
            case(R.id.delete_item):
                dbHelper.deleteItem(index);
                Intent k = new Intent(v.getContext(), Main.class);
                startActivity(k);
                break;
        }
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