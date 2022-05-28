package com.example.myitune;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBasehelper extends SQLiteOpenHelper {



    public DataBasehelper(@Nullable Context context) {
        super(context, util.DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + util.TABLE_NAME + " (" + util.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + util.FULLNAME +"TEXT," + util.USERNAME + "TEXT," + util.PASSWORD + "TEXT)";
        String CREATE_PLAYLIST_TABLE = "CREATE TABLE " + util.TABLE_NAME2 + "(" + util.PLAYLISTID +" INTEGER PRIMARY KEY AUTOINCREMENT , " + util.USERNAME + "TEXT," + util.URL + "TEXT)";
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PLAYLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS '" + util.TABLE_NAME + "'";
        String DROP_PLAYLIST_TABLE = "DROP TABLE IF EXISTS '" + util.TABLE_NAME2 + "'";
        db.execSQL(DROP_PLAYLIST_TABLE);
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);

    }

    public long insertuser(User user)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(util.FULLNAME,user.getFullame());
        contentValues.put(util.USERNAME,user.getUsername());
        contentValues.put(util.PASSWORD,user.getPassword());

        long newinsert = db.insert(util.TABLE_NAME, null, contentValues);
        db.close();

        return newinsert;

    }

    public boolean fetchuser(String username, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(util.TABLE_NAME, new String[]{util.USER_ID}, util.USERNAME + "=? and " + util.PASSWORD + "=?",
                new String[] {username, password}, null, null, null);


        //Cursor cursor = db.ruery("USERS", new String[]{"userid"}, "USERNAME=? and PASSWORD=?", new String[]{username, password}, null, null, null);
        int numberRows = cursor.getCount();
        db.close();
        if(numberRows > 0)
            return true;
        else
            return false;

    }

    public long insertplaylist(Playlist playlist)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(util.USERNAME,playlist.getUsername());
        contentValues.put(util.URL,playlist.getUrl());
        long newrow = db.insert(util.TABLE_NAME2, null, contentValues);
        db.close();
        return newrow;
    }

    public List<Playlist> fetchplaylist(String username)
    {
        List<Playlist> frmlist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM "+ util.TABLE_NAME2 + " where " + util.USERNAME + " = '" + username + "'" , null);

        if(cursor.moveToFirst())
        {
            do{
                String url = cursor.getString(2);
                Playlist playlist = new Playlist(url);
                frmlist.add(playlist);


            }while(cursor.moveToNext());
        }
        return frmlist;
    }

}
