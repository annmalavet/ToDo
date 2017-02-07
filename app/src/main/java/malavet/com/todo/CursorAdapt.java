package malavet.com.todo;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CursorAdapt extends CursorAdapter {
    Activity context;
    Cursor c;
    Context con;
    LayoutInflater mInflater;
    DBHelp dbHelper;
    SQLiteDatabase db;

    public CursorAdapt(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
        mInflater=LayoutInflater.from(context);
        con = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final View view=mInflater.inflate(R.layout.list_row,parent,false);
        return view;
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView name = (TextView) view.findViewById(R.id.name);
        final String index = cursor.getString(cursor.getColumnIndex("_id"));
        String item = cursor.getString(cursor.getColumnIndexOrThrow("item"));
        final TextView detail = (TextView) view.findViewById(R.id.detail);
        final Cursor c = cursor;
        name.setText(item);

    }


}