// GetRowsTasks.java
package com.example.examjava;

import android.database.Cursor;
import android.os.AsyncTask;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

// Клас для завантаження даних з бази даних у фоновому потоці

class GetRowsTask extends AsyncTask<Object, Object, Cursor> {
    private WeakReference<Furniture> act;
    private DatabaseConnector databaseConnector;
    GetRowsTask(Furniture activity){
        act = new WeakReference<>(activity);
        databaseConnector = new DatabaseConnector(activity);
    }

    @Override
    protected Cursor doInBackground(Object... objects) {
        databaseConnector.open(); // відкриття підключення до бд
        return databaseConnector.getTableAllRows(); // одержання даних
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        ArrayList<String> lst = new ArrayList<>(); // масив рядків для виведення на екран у ListView
        int idIndex = cursor.getColumnIndex(DBHelper.TABLE_COLUMN_id);
        int nameIndex = cursor.getColumnIndex(DBHelper.TABLE_COLUMN_name);
        int typeIndex = cursor.getColumnIndex(DBHelper.TABLE_COLUMN_type);

        while (cursor.moveToNext()) {
            String id = cursor.getString(idIndex);
            String name = cursor.getString(nameIndex);
            String type = cursor.getString(typeIndex);

            lst.add("id=" + id + ", name=" + name + ", type=" + type);
        }

        cursor.close(); // закрити курсор
        databaseConnector.close(); // закрити підключення
        act.get().update_list(lst); // оновлення ListView
    }
}

