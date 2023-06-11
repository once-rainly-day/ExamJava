package com.example.examjava;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.AsyncTask;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class GetOrdersTasks extends AsyncTask<Void, Void, Cursor> {
    private WeakReference<OrdersActivity> act;
    private DatabaseConnector databaseConnector;

    GetOrdersTasks(OrdersActivity activity) {
        act = new WeakReference<>(activity);
        databaseConnector = new DatabaseConnector(activity);
    }

    @Override
    protected Cursor doInBackground(Void... voids) {
        databaseConnector.open();
        return databaseConnector.getOrdersAllRows();
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        ArrayList<String> lst = new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            lst.add(
                    "id=" + cursor.getString(cursor.getColumnIndex(DBHelper.ORDERS_COLUMN_id))
                            + ", address=" + cursor.getString(cursor.getColumnIndex(DBHelper.ORDERS_COLUMN_address))
                            + ", name_furnitures=" + cursor.getString(cursor.getColumnIndex(DBHelper.ORDERS_COLUMN_name_furnitures))
                            + ", quantity=" + cursor.getString(cursor.getColumnIndex(DBHelper.ORDERS_COLUMN_quantity))
            );
        }
        cursor.close(); // закрыть курсор после использования
        databaseConnector.close();
        act.get().updateList(lst);
    }
}