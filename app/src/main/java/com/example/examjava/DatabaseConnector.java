// DatabaseConnector.java
package com.example.examjava;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseConnector {
    private static final String DATABASE_NAME = "UserRows";
    private SQLiteDatabase database;
    private DBHelper databaseOpenHelper;

    public DatabaseConnector(Context context) {
        databaseOpenHelper = new DBHelper(context, DATABASE_NAME, null, 1);
    }

    public void open() {
        database = databaseOpenHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            database.close();
        }
    }

    // Добавление ряда в таблицу "Table1"
    public void insertTable1Row(String name, String type) {
        ContentValues row = new ContentValues();
        row.put(DBHelper.TABLE_COLUMN_name, name);
        row.put(DBHelper.TABLE_COLUMN_type, type);
        open();
        database.insert(DBHelper.TABLE, null, row);
        close();
    }

    // Добавление ряда в таблицу "Orders"
    public void insertOrdersRow(String address, String name, String quantity) {
        ContentValues row = new ContentValues();
        row.put(DBHelper.ORDERS_COLUMN_address, address);
        row.put(DBHelper.ORDERS_COLUMN_name_furnitures, name);
        row.put(DBHelper.ORDERS_COLUMN_quantity, quantity);
        open();
        database.insert(DBHelper.TABLE_ORDERS, null, row);
        close();
    }

    public Cursor getTableAllRows() {
        return database.query(DBHelper.TABLE, new String[]{
                        DBHelper.TABLE_COLUMN_id, DBHelper.TABLE_COLUMN_name, DBHelper.TABLE_COLUMN_type},
                null, null, null, null,
                DBHelper.TABLE_COLUMN_type
        );
    }

    public Cursor getOrdersAllRows() {
        return database.query(DBHelper.TABLE_ORDERS, new String[]{
                        DBHelper.ORDERS_COLUMN_id, DBHelper.ORDERS_COLUMN_address,
                        DBHelper.ORDERS_COLUMN_name_furnitures, DBHelper.ORDERS_COLUMN_quantity},
                null, null, null, null,
                DBHelper.ORDERS_COLUMN_name_furnitures
        );
    }
    public void deleteTable1Row(long id) {
        open();
        database.delete(DBHelper.TABLE, "_id=" + id, null);
        close();
    }

    public void deleteOrdersRow(long id) {
        open();
        database.delete(DBHelper.TABLE_ORDERS, "_id=" + id, null);
        close();
    }

    public void editTable1Row(long id, String name, String type) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.TABLE_COLUMN_name, name);
        cv.put(DBHelper.TABLE_COLUMN_type, type);

        String where = "_id=?";
        String[] whereArgs = new String[]{String.valueOf(id)};

        open();
        database.update(DBHelper.TABLE, cv, where, whereArgs);
        close();
    }

    public void editOrdersRow(long id, String address, String name, String quantity) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.ORDERS_COLUMN_address, address);
        cv.put(DBHelper.ORDERS_COLUMN_name_furnitures, name);
        cv.put(DBHelper.ORDERS_COLUMN_quantity, quantity);

        String where = "_id=?";
        String[] whereArgs = new String[]{String.valueOf(id)};

        open();
        database.update(DBHelper.TABLE_ORDERS, cv, where, whereArgs);
        close();
    }
}