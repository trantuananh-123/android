package com.example.ktra2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ktra2.Model.Job;
import com.example.ktra2.Model.JobStatistic;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Cong_Viec.db";
    private static final Integer DB_VERSION = 1;
    private static final String TABLE_NAME = "job";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE job(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "content TEXT, " +
                "date TEXT, " +
                "status TEXT, " +
                "type BOOLEAN" +
                ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Job> getAll() {
        List<Job> jobList = new ArrayList<>();
        String orderClause = "date DESC";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, orderClause);
        while (cursor.moveToNext()) {
            Job job = new Job();
            job.setId(cursor.getInt(0));
            job.setName(cursor.getString(1));
            job.setContent(cursor.getString(2));
            job.setDate(cursor.getString(3));
            job.setStatus(cursor.getString(4));
            job.setType(cursor.getInt(5) != 0);
            jobList.add(job);
        }
        return jobList;
    }

    public long add(Job job) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", job.getName());
        contentValues.put("content", job.getContent());
        contentValues.put("date", job.getDate());
        contentValues.put("status", job.getStatus());
        contentValues.put("type", job.getType());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public long update(Job job) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", job.getName());
        contentValues.put("content", job.getContent());
        contentValues.put("date", job.getDate());
        contentValues.put("status", job.getStatus());
        contentValues.put("type", job.getType());
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(job.getId())};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update(TABLE_NAME, contentValues, whereClause, whereArgs);
    }

    public long delete(Integer id) {
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, whereClause, whereArgs);
    }

    public List<Job> searchByName(String key) {
        List<Job> jobList = new ArrayList<>();
        String orderClause = "date ASC";
        String whereClause = "name like ?";
        String[] whereArgs = {"%" + key + "%"};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, whereClause, whereArgs, null, null, orderClause);
        while (cursor.moveToNext()) {
            Job job = new Job();
            job.setId(cursor.getInt(0));
            job.setName(cursor.getString(1));
            job.setContent(cursor.getString(2));
            job.setDate(cursor.getString(3));
            job.setStatus(cursor.getString(4));
            job.setType(cursor.getInt(5) != 0);
            jobList.add(job);
        }
        return jobList;
    }

    public List<JobStatistic> statisticByStatus(String key) {
        List<JobStatistic> jobStatisticList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "SELECT j.status, COUNT(j.id) AS total FROM job j WHERE (LOWER(?) = LOWER('Tat ca') OR LOWER(status) = LOWER(?)) GROUP BY status ORDER BY total DESC";
        String[] whereArgs = {key, key};
        Cursor cursor = sqLiteDatabase.rawQuery(sql, whereArgs);
        while (cursor.moveToNext()) {
            String status = cursor.getString(0);
            Integer total = cursor.getInt(1);
            JobStatistic jobStatistic = new JobStatistic(total, status);
            jobStatisticList.add(jobStatistic);
        }
        return jobStatisticList;
    }
}
