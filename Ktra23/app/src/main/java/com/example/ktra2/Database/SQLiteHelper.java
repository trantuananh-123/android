package com.example.ktra2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ktra2.Model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Nhan_Vien.db";
    public static final Integer DB_VERSION = 1;
    public static final String TABLE_NAME = "employee";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE employee(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "phone TEXT, " +
                "year INTEGER, " +
                "gender TEXT, " +
                "skill TEXT" +
                ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Employee> getAll() {
        List<Employee> employeeList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Employee employee = new Employee();
            employee.setId(cursor.getInt(0));
            employee.setName(cursor.getString(1));
            employee.setPhone(cursor.getString(2));
            employee.setYear(cursor.getInt(3));
            employee.setGender(cursor.getString(4));
            employee.setSkill(cursor.getString(5));
            employeeList.add(employee);
        }
        return employeeList;
    }

    public long add(Employee employee) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", employee.getId());
        contentValues.put("name", employee.getName());
        contentValues.put("phone", employee.getPhone());
        contentValues.put("year", employee.getYear());
        contentValues.put("gender", employee.getGender());
        contentValues.put("skill", employee.getSkill());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public long update(Employee employee) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", employee.getId());
        contentValues.put("name", employee.getName());
        contentValues.put("phone", employee.getPhone());
        contentValues.put("year", employee.getYear());
        contentValues.put("gender", employee.getGender());
        contentValues.put("skill", employee.getSkill());

        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(employee.getId())};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update(TABLE_NAME, contentValues, whereClause, whereArgs);
    }

    public long detele(Integer id) {
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, whereClause, whereArgs);
    }

    public List<Employee> searchBySkill(String skill) {
        List<Employee> employeeList = new ArrayList<>();
        String whereClause = "skill LIKE ?";
        String[] whereArgs = {'%' + skill + '%'};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, whereClause, whereArgs, null, null, null);
        while (cursor.moveToNext()) {
            Employee employee = new Employee();
            employee.setId(cursor.getInt(0));
            employee.setName(cursor.getString(1));
            employee.setPhone(cursor.getString(2));
            employee.setYear(cursor.getInt(3));
            employee.setGender(cursor.getString(4));
            employee.setSkill(cursor.getString(5));
            employeeList.add(employee);
        }
        return employeeList;
    }

    public HashMap<String, Integer> statistic() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String query1 = "SELECT COUNT(*) FROM employee";
        String query2 = "SELECT COUNT(*) FROM employee WHERE skill LIKE '%web%'";
        String query3 = "SELECT COUNT(*) FROM employee WHERE skill LIKE '%android%'";
        String query4 = "SELECT COUNT(*) FROM employee WHERE skill LIKE '%ios%'";

        Cursor cursor1 = sqLiteDatabase.rawQuery(query1, null);
        Cursor cursor2 = sqLiteDatabase.rawQuery(query2, null);
        Cursor cursor3 = sqLiteDatabase.rawQuery(query3, null);
        Cursor cursor4 = sqLiteDatabase.rawQuery(query4, null);
        while (cursor1.moveToNext()) {
            hashMap.put("Total", cursor1.getInt(0));
        }
        while (cursor2.moveToNext()) {
            hashMap.put("web", cursor2.getInt(0));
        }
        while (cursor3.moveToNext()) {
            hashMap.put("android", cursor3.getInt(0));
        }
        while (cursor4.moveToNext()) {
            hashMap.put("ios", cursor4.getInt(0));
        }
        return hashMap;
    }
}
