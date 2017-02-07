package malavet.com.todo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DBHelp extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "todoitems.db";
    public static final String KEY_ROWID = "_id";
    public static String TABLE = "TODO";
    public static String ITEM = "item";
    public static String DETAIL = "detail";

    private static final String DATABASE_CREATE =
            "create table " + TABLE + "(" +
                    KEY_ROWID + " integer primary key autoincrement, " +
                    ITEM + " text not null, " +
                    DETAIL + " text not null );";
    SQLiteDatabase db = getWritableDatabase();

    public DBHelp(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public ArrayList<String> something() {
        ArrayList<String> aList = null;
        aList = getAllItems();
        return aList;
    }

    public SQLiteDatabase openDB(SQLiteDatabase db) {
        db = this.getWritableDatabase();
        return db;
    }
    public ArrayList<String> getAllItems() {
        ArrayList<String> mArrayList = new ArrayList<String>();
        Cursor cursor = null;
        cursor =  db.query(true, TABLE, new String[]{KEY_ROWID, ITEM,
                DETAIL}, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                mArrayList.add(cursor.getString(cursor.getColumnIndex(KEY_ROWID)));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return mArrayList;

    }
    public Cursor getC() {
        Cursor cursor = null;
        cursor =  db.query(true, TABLE, new String[] {KEY_ROWID, ITEM,
                DETAIL }, null, null, null, null, null, null);

        return cursor;

    }

    public void insertItem (String detail, String item) {
        openDB(db);
        ContentValues values = new ContentValues();
        values.put(ITEM, item);
        values.put(DETAIL, detail);
        db.insert(TABLE, null, values);

    }


    public void deleteItem (String s) {
        openDB(db);
        db.delete(TABLE, KEY_ROWID + "=" + s, null);

    }
    public void updateRow(String s, String t, String index){
        openDB(db);
        ContentValues cv = new ContentValues();
        cv.put(ITEM, s);
        cv.put(DETAIL, t);
        db.update(TABLE, cv, KEY_ROWID + "=" + index, null);

        }



    public ArrayList<String> getRowArray(String s) {
        Cursor cursor = null;
        ArrayList<String> mArrayList = new ArrayList<String>();
        cursor =  db.query(true, TABLE, new String[] {KEY_ROWID, ITEM,
                        DETAIL }, KEY_ROWID + "=?",
                new String[] { s }, null, null, null, null, null);
        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            do {
                mArrayList.add(cursor.getString(cursor.getColumnIndex(KEY_ROWID)));
                mArrayList.add(cursor.getString(cursor.getColumnIndex(ITEM)));
                mArrayList.add(cursor.getString(cursor.getColumnIndex(DETAIL)));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return mArrayList;

    }
}