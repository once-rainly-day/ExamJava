// DBHelper.java
package com.example.examjava;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Клас використовується для створення та оновлення бази даних

class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE = "Table1"; // назва таблиці
    public static final String TABLE_COLUMN_id = "_id"; // змінна для збереження айді елемента
    public static final String TABLE_COLUMN_name = "name"; // змінна для збереження текстового рядка з назвою товару
    public static final String TABLE_COLUMN_type = "type"; // змінна для збереження текстового рядка з типом меблів

    public static final String TABLE_ORDERS = "Orders"; // название таблицы Orders
    public static final String ORDERS_COLUMN_id = "_id"; // айди элемента
    public static final String ORDERS_COLUMN_address = "address"; // адрес
    public static final String ORDERS_COLUMN_name_furnitures = "name_furnitures"; // название мебели
    public static final String ORDERS_COLUMN_quantity = "quantity"; // количество


    public DBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, databaseName, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создание таблицы Table1
        db.execSQL("CREATE TABLE " + TABLE + "( "
                + TABLE_COLUMN_id + " integer primary key autoincrement, "
                + TABLE_COLUMN_name + " TEXT, "
                + TABLE_COLUMN_type + " TEXT"
                + " )"
        );

        // Создание таблицы Orders
        db.execSQL("CREATE TABLE " + TABLE_ORDERS + "( "
                + ORDERS_COLUMN_id + " integer primary key autoincrement, "
                + ORDERS_COLUMN_address + " TEXT, "
                + ORDERS_COLUMN_name_furnitures + " TEXT, "
                + ORDERS_COLUMN_quantity + " INTEGER, "
                +  "FOREIGN KEY(" + TABLE_COLUMN_id + ") REFERENCES " + TABLE + "(" + TABLE_COLUMN_id + ")"
                + " )"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }
}
