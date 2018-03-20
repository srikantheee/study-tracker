package com.mak.studytracker.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.internal.bind.util.ISO8601Utils;
import com.mak.studytracker.models.Subject;


import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "mystudybase";

    // Posts table name
    private static final String TABLE_BLOGS = "blogs";
    private static final String TABLE_SUBJECTS = "subjects";
    private static final String TABLE_LABELS = "labels";
    private static final String TABLE_SUBJECT_LABELS = "postlabels";

    // Posts Table Columns names
    private static final String BLOG_ID = "blog_id";
    private static final String BLOG_UPDATED = "blog_updated";
    private static final String SUBJECT_ID = "subject_id";
    private static final String SUBJECT_NAME = "subject_name";
    private static final String SUBJECT_UNITS_NAME = "subject_units_name";
    private static final String SUBJECT_UNITS_VALUE = "subject_units_value";
    private static final String SUBJECT_TARGET_DATE = "subject_target_date";
    private static final String SUBJECT_BEGIN_DATE = "subject_begin_date";
    private static final String SUBJECT_SELECTED_DAYS = "subject_selected_days";
    private static final String SUBJECT_CURRENT_PROGRESS = "subject_current_progress";

   private static final String LABEL_ID = "label_id";
    private static final String LABEL_NAME = "label_name";
    private static final String SUBJECT_LABEL_ID = "subject_label_id";
    private static final String SUBJECT_LABEL_LABEL_ID = "subject_label_label_id";
    private static final String SUBJECT_LABEL_SUBJECT_ID = "subject_label_subject_id";


    private static final String KEY_LIKE_VALUE = "like_value";

    private static final int CONTENT_TEXT_LENGTH=120;
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_SUBJECTS_TABLE = "CREATE TABLE " + TABLE_SUBJECTS + "("
                + SUBJECT_ID + " INTEGER PRIMARY KEY   AUTOINCREMENT,"
                + SUBJECT_NAME + " TEXT,"
                + SUBJECT_UNITS_NAME + " TEXT,"
                + SUBJECT_UNITS_VALUE + " INTEGER,"
                + SUBJECT_TARGET_DATE + " TEXT,"
                + SUBJECT_BEGIN_DATE + " TEXT,"
                + SUBJECT_SELECTED_DAYS + " TEXT,"
                + SUBJECT_CURRENT_PROGRESS + " INTEGER"

                + ")";
        db.execSQL(CREATE_SUBJECTS_TABLE);
        String CREATE_LABELS_TABLE = "CREATE TABLE " + TABLE_LABELS + "("
                + LABEL_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT,"
                + LABEL_NAME + " TEXT"
                + ")";
        db.execSQL(CREATE_LABELS_TABLE);
        String CREATE_SUBJECT_LABELS_TABLE = "CREATE TABLE " + TABLE_SUBJECT_LABELS + "("
                + SUBJECT_LABEL_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT,"
                + SUBJECT_LABEL_LABEL_ID + " INTEGER"
                + SUBJECT_LABEL_SUBJECT_ID + " TEXT"
                + ")";
        db.execSQL(CREATE_SUBJECT_LABELS_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BLOGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LABELS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT_LABELS);


        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    // Adding new like
    public void addBlogUpdated(String updated) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BLOG_ID, URLHelper.BLOG_ID); // Like Name
        values.put(BLOG_UPDATED, updated);
        // Inserting Row
        db.insert(TABLE_BLOGS, null, values);
        db.close(); // Closing database connection

    }

    // Updating single like
    public int updateBlogUpdated(String updated) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BLOG_ID, URLHelper.BLOG_ID); // Like Name
        values.put(BLOG_UPDATED, updated);

        // updating row
        return db.update(TABLE_BLOGS, values, BLOG_ID + " = ?",
                new String[]{URLHelper.BLOG_ID});
    }

    // Getting single like
    public String getBlogUpdated() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BLOGS, new String[]{
                        BLOG_ID,
                        BLOG_UPDATED,
                }, BLOG_ID + "=?",
                new String[]{URLHelper.BLOG_ID}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        // return like
        return cursor.getString(1);
    }


    // Adding new like
    public void addSubject(Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
         values.put(SUBJECT_NAME, subject.getName());
        values.put(SUBJECT_UNITS_NAME, subject.getUnitsName());
        values.put(SUBJECT_UNITS_VALUE, subject.getUnitsValue());
        values.put(SUBJECT_TARGET_DATE, subject.getTargetDate());


        values.put(SUBJECT_BEGIN_DATE, subject.getBeginDate());
        values.put(SUBJECT_SELECTED_DAYS, subject.getSelectedDays());
        values.put(SUBJECT_CURRENT_PROGRESS, subject.getCurrentProgress());

        // Inserting Row
        db.insert(TABLE_SUBJECTS, null, values);
        db.close(); // Closing database connection

    }

    // Getting single subject
    public Subject getSubject(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SUBJECTS, new String[]{
                        SUBJECT_ID,
                        SUBJECT_NAME,
                        SUBJECT_UNITS_NAME,
                        SUBJECT_UNITS_VALUE,
                        SUBJECT_BEGIN_DATE,
                        SUBJECT_TARGET_DATE,
                        SUBJECT_SELECTED_DAYS,
                        SUBJECT_CURRENT_PROGRESS,


                }, SUBJECT_ID + "=?",
                new String[]{id+""}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        ArrayList<String> labels = new ArrayList<>();
        labels.add("klsdaf");
        Subject subject = new Subject(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getInt(7)
          );
        cursor.close();
        db.close();
        // return like
        return subject;
    }


    // Getting All Likes
    public List<Subject> getAllPosts() {
        List<Subject> subjectList = new ArrayList<Subject>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SUBJECTS + "  ORDER BY " + SUBJECT_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Subject subject = new Subject();

                subject.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SUBJECT_ID))));
                subject.setName(cursor.getString(cursor.getColumnIndex(SUBJECT_NAME)));
                subject.setUnitsName(cursor.getString(cursor.getColumnIndex(SUBJECT_UNITS_NAME)));
                subject.setUnitsValue(cursor.getInt(cursor.getColumnIndex(SUBJECT_UNITS_VALUE)));
                subject.setBeginDate(cursor.getString(cursor.getColumnIndex(SUBJECT_BEGIN_DATE)));
                subject.setTargetDate(cursor.getString(cursor.getColumnIndex(SUBJECT_TARGET_DATE)));
                subject.setCurrentProgress(cursor.getInt(cursor.getColumnIndex(SUBJECT_CURRENT_PROGRESS)));
                subject.setSelectedDays(cursor.getString(cursor.getColumnIndex(SUBJECT_SELECTED_DAYS)));
                // Adding like to list
                subjectList.add(subject);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return like list
        return subjectList;
    }


    // Updating single like
    public int updateSubjectProgress(Subject subject,int updateValue) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(SUBJECT_CURRENT_PROGRESS, (subject.getCurrentProgress()+updateValue));


        // updating row
        return db.update(TABLE_SUBJECTS, values, SUBJECT_ID + " = ?",
                new String[]{String.valueOf(subject.getId())});
    }

    // Updating single like
    public int updateSubject(Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SUBJECT_ID, subject.getId()); // Like Name
        values.put(SUBJECT_NAME, subject.getName());
        values.put(SUBJECT_UNITS_NAME, subject.getUnitsName());
        values.put(SUBJECT_UNITS_VALUE, subject.getUnitsValue());

        values.put(SUBJECT_BEGIN_DATE, subject.getBeginDate());
        values.put(SUBJECT_TARGET_DATE, subject.getTargetDate());
        values.put(SUBJECT_SELECTED_DAYS, subject.getSelectedDays());
        values.put(SUBJECT_CURRENT_PROGRESS, subject.getCurrentProgress());


        // updating row
        return db.update(TABLE_SUBJECTS, values, SUBJECT_ID + " = ?",
                new String[]{String.valueOf(subject.getId())});
    }


    // Deleting single like
    public void deletePost(Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SUBJECTS, SUBJECT_ID + " = ?",
                new String[]{String.valueOf(subject.getId())});
        db.close();
    }


    // Getting likes Count
    public int getPostsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SUBJECTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public boolean getPostExist(String subject_id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SUBJECTS, new String[]{SUBJECT_ID,
                }, SUBJECT_ID + "=?",
                new String[]{String.valueOf(subject_id)}, null, null, null, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();

        // return count
        return exists;

    }


}