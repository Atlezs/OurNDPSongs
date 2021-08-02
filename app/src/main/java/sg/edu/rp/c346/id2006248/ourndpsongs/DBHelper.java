package sg.edu.rp.c346.id2006248.ourndpsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "simpleSongs.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_Song = "Song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_Song_TITLE = "Song_content";
    private static final String COLUMN_Song_SINGERS = "Song_singers";
    private static final String COLUMN_Song_YEAR = "Song_year";
    private static final String COLUMN_Song_STARS = "Song_stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql = "CREATE TABLE " + TABLE_Song + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_Song_TITLE + " TEXT,"
                + COLUMN_Song_SINGERS + " TEXT,"
                + COLUMN_Song_YEAR + " INTERGER,"
                + COLUMN_Song_STARS + " INTEGER ) ";
        db.execSQL(createSongTableSql);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("ALTER TABLE " + TABLE_Song + " ADD COLUMN  module_name TEXT ");
    }


    public long insertSong(String songTitle, String songSingers, Integer songYear, Integer songStars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_Song_TITLE, songTitle);
        values.put(COLUMN_Song_SINGERS, songSingers);
        values.put(COLUMN_Song_YEAR, songYear);
        values.put(COLUMN_Song_STARS, songStars);
        long result = db.insert(TABLE_Song, null, values);
        db.close();
        return result;
    }


    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> Songs = new ArrayList<Song>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_Song_TITLE + ", "
                + COLUMN_Song_SINGERS + ", "
                + COLUMN_Song_YEAR + ", "
                + COLUMN_Song_STARS
                + " FROM " + TABLE_Song;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String songTitle = cursor.getString(1);
                String songSingers = cursor.getString(2);
                int songYear = cursor.getInt(3);
                int songStars = cursor.getInt(4);
                Song Song = new Song(songTitle, songSingers, songYear, songStars);
                Song.setId(id);
                Songs.add(Song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Songs;
    }

    public ArrayList<Song> getAllSongsYear() {
        ArrayList<Song> Songs = new ArrayList<Song>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_Song_TITLE + ", "
                + COLUMN_Song_SINGERS + ", "
                + COLUMN_Song_YEAR + ", "
                + COLUMN_Song_STARS
                + " FROM " + TABLE_Song;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int songYear = cursor.getInt(3);
                Song Song = new Song(songYear);
                Songs.add(Song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Songs;
    }

    public int updateSong(Song data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_Song_TITLE, data.getTitle());
        values.put(COLUMN_Song_SINGERS, data.getSingers());
        values.put(COLUMN_Song_YEAR, data.getYear());
        values.put(COLUMN_Song_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_Song, values, condition, args);
        db.close();
        return result;
    }


    public int deleteSong(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_Song, condition, args);
        db.close();
        return result;
    }


    public ArrayList<Song> getAllSongs(int keyword) {
        ArrayList<Song> Songs = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_Song_TITLE, COLUMN_Song_SINGERS, COLUMN_Song_YEAR, COLUMN_Song_STARS};
        String condition = COLUMN_Song_STARS + " Like ?";
        String[] args = {"%" + keyword + "%"};
        Cursor cursor = db.query(TABLE_Song, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String songTitle = cursor.getString(1);
                String songSingers = cursor.getString(2);
                int songYear = cursor.getInt(3);
                int songStars = cursor.getInt(4);
                Song Song = new Song(songTitle, songSingers, songYear, songStars);
                Song.setId(id);
                Songs.add(Song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Songs;
    }

    public ArrayList<Song> getAllSongsYear(int keyword) {
        ArrayList<Song> Songs = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_Song_TITLE, COLUMN_Song_SINGERS, COLUMN_Song_YEAR, COLUMN_Song_STARS};
        String condition = COLUMN_Song_YEAR + " Like ?";
        String[] args = {"%" + keyword + "%"};
        Cursor cursor = db.query(TABLE_Song, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String songTitle = cursor.getString(1);
                String songSingers = cursor.getString(2);
                int songYear = cursor.getInt(3);
                int songStars = cursor.getInt(4);
                Song Song = new Song(songTitle, songSingers, songYear, songStars);
                Song.setId(id);
                Songs.add(Song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Songs;
    }


}
