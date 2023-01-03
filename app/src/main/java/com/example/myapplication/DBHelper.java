package com.example.myapplication;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=4;
    private static final String DATABASE_NAME="todo.db";
    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table "+ ContactsContract.CommonDataKinds.Note.TABLE+"("
                + ContactsContract.CommonDataKinds.Note.KEY_id+" integer primary key autoincrement, "
                + ContactsContract.CommonDataKinds.Note.KEY_title+" text, "
                + ContactsContract.CommonDataKinds.Note.KEY_context+" text)";
        sqLiteDatabase.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+ ContactsContract.CommonDataKinds.Note.TABLE);
        onCreate(sqLiteDatabase);
    }
}

