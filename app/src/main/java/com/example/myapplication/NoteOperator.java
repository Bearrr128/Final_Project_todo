package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class NoteOperator {
    private DBHelper dbHelper;
    private int title_col,context_col,id_col;

    public NoteOperator(Context context) {

        dbHelper = new DBHelper(context);
    }

    public boolean insert(Note note) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Note.KEY_title, note.title);
        contentValues.put(Note.KEY_context, note.context);

        long note_id = db.insert(Note.TABLE, null, contentValues);
        db.close();
        if (note_id != -1)
            return true;
        else
            return false;
    }


    public void delete(int note_id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Note.TABLE, Note.KEY_id + "=?", new String[]{String.valueOf(note_id)});
        db.close();
    }


    public ArrayList<HashMap<String, String>> getNoteList() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select " + Note.KEY_id + "," + Note.KEY_title + "," + Note.KEY_context +
                " from " + Note.TABLE;

        ArrayList<HashMap<String, String>> noteList = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> note = new HashMap<String, String>();
            title_col=cursor.getColumnIndex(Note.KEY_id);
            id_col=cursor.getColumnIndex(Note.KEY_title);

            note.put("id", cursor.getString(id_col));
            note.put("title", cursor.getString(title_col));
            noteList.add(note);
        }
        cursor.close();
        db.close();
        return noteList;
    }

    public Note getNoteById(int id) {



        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select " + Note.KEY_title + "," + Note.KEY_context +
                " from " + Note.TABLE + " where " + Note.KEY_id + "=?";
        Note note = new Note();
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});



        while (cursor.moveToNext()) {
            title_col=cursor.getColumnIndex(Note.KEY_title);
            context_col=cursor.getColumnIndex(Note.KEY_context);

            note.title = cursor.getString(title_col);
            note.context = cursor.getString(context_col);

        }
        cursor.close();
        db.close();
        return note;
    }

    public void update(Note note) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Note.KEY_title, note.title);
        contentValues.put(Note.KEY_context, note.context);

        db.update(Note.TABLE, contentValues, Note.KEY_id + "=?", new String[]{String.valueOf(note.note_id)});
        db.close();
    }
}