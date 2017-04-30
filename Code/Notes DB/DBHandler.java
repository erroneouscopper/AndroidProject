package team3.phms;

/**
 * Created by kjorg_000 on 4/29/2017.
 */

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;


public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "phms.db";
    private static final String TABLE_NOTES = "notes";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NOTE = "note";



    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NOTES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                COLUMN_NAME + " TEXT " +
                COLUMN_NOTE + " TEXT " + ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE_IF_EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

    //Add new row to the database
    public void addNote(NotesList note){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, note.get_name());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NOTES, null, values);
    }

    //Delete a note from database
    public void deleteNote(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NOTES + " WHERE " + COLUMN_NAME + "=\"" + name + "\";");
    }

    public String getnote(String name){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT FROM " + TABLE_NOTES + " WHERE " + COLUMN_NAME + "=\"" + name + "\";";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        String note = c.getString(c.getColumnIndex("note"));
        return note;
    }

    //Print out the database as a string
    public String[] dbToString(){
        String[] dbString = {""};
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NOTES + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        int i = 0;
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("name")) != null){
                dbString[i] = c.getString(c.getColumnIndex("name"));
                i++;
            }
        }
        return dbString;
    }
}
