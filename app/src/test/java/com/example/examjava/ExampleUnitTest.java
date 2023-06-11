/*package com.example.examjava;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ExampleUnitTest {
    private DatabaseConnector databaseConnector;

    @Before
    public void setup() {
        Context context = ApplicationProvider.getApplicationContext();
        databaseConnector = new DatabaseConnector(context);
        databaseConnector.open();
    }

    @After
    public void cleanup() {
        databaseConnector.close();
    }

    @Test
    public void table1RowAddition_isCorrect() {
        // Запрограммируйте добавление ряда в таблицу Table1
        String name = "Test Name";
        String type = "Test Type";

        databaseConnector.insertTable1Row(name, type);

        Cursor cursor = databaseConnector.getTableAllRows();
        int rowCount = cursor.getCount();
        cursor.close();

        assertEquals(1, rowCount);
    }

    @Test
    public void table1RowUpdate_isCorrect() {
        // Запрограммируйте обновление ряда в таблице Table1
        String newName = "Updated Name";
        String newType = "Updated Type";

        Cursor cursorBeforeUpdate = databaseConnector.getTableAllRows();
        cursorBeforeUpdate.moveToFirst();
        long rowId = cursorBeforeUpdate.getLong(cursorBeforeUpdate.getColumnIndex(DBHelper.TABLE_COLUMN_id));
        cursorBeforeUpdate.close();

        databaseConnector.editTable1Row(rowId, newName, newType);

        Cursor cursorAfterUpdate = databaseConnector.getTableAllRows();
        cursorAfterUpdate.moveToFirst();
        String updatedName = cursorAfterUpdate.getString(cursorAfterUpdate.getColumnIndex(DBHelper.TABLE_COLUMN_name));
        String updatedType = cursorAfterUpdate.getString(cursorAfterUpdate.getColumnIndex(DBHelper.TABLE_COLUMN_type));
        cursorAfterUpdate.close();

        assertEquals(newName, updatedName);
        assertEquals(newType, updatedType);
    }

    @Test
    public void table1RowDeletion_isCorrect() {
        // Запрограммируйте удаление ряда из таблицы Table1
        Cursor cursorBeforeDeletion = databaseConnector.getTableAllRows();
        cursorBeforeDeletion.moveToFirst();
        long rowId = cursorBeforeDeletion.getLong(cursorBeforeDeletion.getColumnIndex(DBHelper.TABLE_COLUMN_id));
        cursorBeforeDeletion.close();

        databaseConnector.deleteTable1Row(rowId);

        Cursor cursorAfterDeletion = databaseConnector.getTableAllRows();
        int rowCount = cursorAfterDeletion.getCount();
        cursorAfterDeletion.close();

        assertEquals(0, rowCount);
    }

    @Test
    public void ordersRowAddition_isCorrect() {
        // Запрограммируйте добавление ряда в таблицу Orders
        String address = "Test Address";
        String name = "Test Name";
        String quantity = "Test Quantity";

        databaseConnector.insertOrdersRow(address, name, quantity);

        Cursor cursor = databaseConnector.getOrdersAllRows();
        int rowCount = cursor.getCount();
        cursor.close();

        assertEquals(1, rowCount);
    }

    @Test
    public void ordersRowUpdate_isCorrect() {
        // Запрограммируйте обновление ряда в таблице Orders
        String newAddress = "Updated Address";
        String newName = "Updated Name";
        String newQuantity = "Updated Quantity";

        Cursor cursorBeforeUpdate = databaseConnector.getOrdersAllRows();
        cursorBeforeUpdate.moveToFirst();
        long rowId = cursorBeforeUpdate.getLong(cursorBeforeUpdate.getColumnIndex(DBHelper.ORDERS_COLUMN_id));
        cursorBeforeUpdate.close();

        databaseConnector.editOrdersRow(rowId, newAddress, newName, newQuantity);

        Cursor cursorAfterUpdate = databaseConnector.getOrdersAllRows();
        cursorAfterUpdate.moveToFirst();
        String updatedAddress = cursorAfterUpdate.getString(cursorAfterUpdate.getColumnIndex(DBHelper.ORDERS_COLUMN_address));
        String updatedName = cursorAfterUpdate.getString(cursorAfterUpdate.getColumnIndex(DBHelper.ORDERS_COLUMN_name_furnitures));
        String updatedQuantity = cursorAfterUpdate.getString(cursorAfterUpdate.getColumnIndex(DBHelper.ORDERS_COLUMN_quantity));
        cursorAfterUpdate.close();

        assertEquals(newAddress, updatedAddress);
        assertEquals(newName, updatedName);
        assertEquals(newQuantity, updatedQuantity);
    }

    @Test
    public void ordersRowDeletion_isCorrect() {
        // Запрограммируйте удаление ряда из таблицы Orders
        Cursor cursorBeforeDeletion = databaseConnector.getOrdersAllRows();
        cursorBeforeDeletion.moveToFirst();
        long rowId = cursorBeforeDeletion.getLong(cursorBeforeDeletion.getColumnIndex(DBHelper.ORDERS_COLUMN_id));
        cursorBeforeDeletion.close();

        databaseConnector.deleteOrdersRow(rowId);

        Cursor cursorAfterDeletion = databaseConnector.getOrdersAllRows();
        int rowCount = cursorAfterDeletion.getCount();
        cursorAfterDeletion.close();

        assertEquals(0, rowCount);
    }
}
*/