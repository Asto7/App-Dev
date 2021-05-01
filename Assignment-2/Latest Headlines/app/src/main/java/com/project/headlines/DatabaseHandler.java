package com.project.headlines;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.project.headlines.model.News;

import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "saved_headline";

    // User table name
    private static final String TABLE_HEADLINE = "saved_headline";

    // User Table Columns names
    private static final String COLUMN_SAVE_ITEM_TITLE = "save_item_title";
    private static final String COLUMN_SAVE_ITEM_DESCRIPTION = "save_item_description";
    private static final String COLUMN_SAVE_ITEM_URL = "save_item_url";
    private static final String COLUMN_SAVE_ITEM_URL_TO_IMAGE = "save_item_url_to_image";
    private static final String COLUMN_SAVE_ITEM_PUBLISHED_DATE = "save_item_published_date";
    private static final String COLUMN_SAVE_ITEM_SOURCE_NAME = "save_item_source_name";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_HEADLINE + "("
                + COLUMN_SAVE_ITEM_TITLE + " TEXT,"
                + COLUMN_SAVE_ITEM_DESCRIPTION + " TEXT,"
                + COLUMN_SAVE_ITEM_URL + " TEXT,"
                + COLUMN_SAVE_ITEM_URL_TO_IMAGE + " TEXT,"
                + COLUMN_SAVE_ITEM_PUBLISHED_DATE + " TEXT,"
                + COLUMN_SAVE_ITEM_SOURCE_NAME + " TEXT" + ")";

        db.execSQL(CREATE_PRODUCT_TABLE);
    }

    public boolean checkIfAlreadyExists(String id) {
        String[] columns = {
                COLUMN_SAVE_ITEM_TITLE,
                COLUMN_SAVE_ITEM_DESCRIPTION,
                COLUMN_SAVE_ITEM_URL,
                COLUMN_SAVE_ITEM_URL_TO_IMAGE,
                COLUMN_SAVE_ITEM_PUBLISHED_DATE,
                COLUMN_SAVE_ITEM_SOURCE_NAME};

        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_SAVE_ITEM_URL + " = ?";
        String[] selectionArgs = {id};

        Cursor cursor = db.query(TABLE_HEADLINE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;
    }

    public void addNews(News news) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_SAVE_ITEM_TITLE, news.getNewsTitle());
        values.put(COLUMN_SAVE_ITEM_DESCRIPTION, news.getNewsDescription());
        values.put(COLUMN_SAVE_ITEM_URL, news.getNewsUrl());
        values.put(COLUMN_SAVE_ITEM_URL_TO_IMAGE, news.getNewsImage());
        values.put(COLUMN_SAVE_ITEM_PUBLISHED_DATE, news.getNewsPublishedDate());
        values.put(COLUMN_SAVE_ITEM_SOURCE_NAME, news.getSource().getSourceName());

        db.insert(TABLE_HEADLINE, null, values);
        db.close();
    }

    public void removeNews(News news) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_HEADLINE + " WHERE " + COLUMN_SAVE_ITEM_URL + " = \"" + news.getNewsUrl() + "\"";
        db.execSQL(query);
    }

    public List<News> getAllNews() {
        String[] columns = {
                COLUMN_SAVE_ITEM_TITLE,
                COLUMN_SAVE_ITEM_DESCRIPTION,
                COLUMN_SAVE_ITEM_URL,
                COLUMN_SAVE_ITEM_URL_TO_IMAGE,
                COLUMN_SAVE_ITEM_PUBLISHED_DATE,
                COLUMN_SAVE_ITEM_SOURCE_NAME
        };

        String sortOrder = COLUMN_SAVE_ITEM_PUBLISHED_DATE + " DESC";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_HEADLINE,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);


        List<News> newsList = new ArrayList<News>();
        if (cursor.moveToFirst()) {
            do {
                News news = new News();
                News.Source src = new News.Source();

                news.setNewsTitle((cursor.getString(cursor.getColumnIndex(COLUMN_SAVE_ITEM_TITLE))));
                news.setNewsDescription(cursor.getString(cursor.getColumnIndex(COLUMN_SAVE_ITEM_DESCRIPTION)));
                news.setNewsUrl((cursor.getString(cursor.getColumnIndex(COLUMN_SAVE_ITEM_URL))));
                news.setNewsImage((cursor.getString(cursor.getColumnIndex(COLUMN_SAVE_ITEM_URL_TO_IMAGE))));
                news.setNewsPublishedDate(stringToDate((cursor.getString(cursor.getColumnIndex(COLUMN_SAVE_ITEM_PUBLISHED_DATE))), "dd/MM/yyyy"));
                src.setSourceName(cursor.getString(cursor.getColumnIndex(COLUMN_SAVE_ITEM_SOURCE_NAME)));

                news.setSource(src);
                newsList.add(news);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return newsList;
    }

    private Date stringToDate(String aDate, String aFormat) {
        if (aDate == null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    }
}