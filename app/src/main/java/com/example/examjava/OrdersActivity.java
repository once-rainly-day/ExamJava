package com.example.examjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {
    private static final String TAG = "OrdersActivity";
    private ArrayList<String> lst;
    private long rowId; // переменная для хранения ID выбранного элемента, используется для редактирования элемента

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiity_orders);

        ListView lv = findViewById(R.id.lv_orders);
        registerForContextMenu(lv); // регистрация компонента ListView для контекстного меню
        Button backButton = findViewById(R.id.back);

        backButton.setOnClickListener(v -> {
            finish(); // Закрываем текущую активность и возвращаемся к предыдущей
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshScreen();
    }

    // запуск обновления экрана информацией из базы данных в фоновом потоке
    void refreshScreen() {
        new GetOrdersTasks(this).execute();
    }

    public void add_btn_clicked(View view) {
        // получение ссылок на экземпляры компонентов
        String address = ((EditText) findViewById(R.id.et_address)).getText().toString();
        String name = ((EditText) findViewById(R.id.et_name)).getText().toString();
        String quantity = ((EditText) findViewById(R.id.et_quantity)).getText().toString();

        // подключение к БД
        DatabaseConnector databaseConnector = new DatabaseConnector(this);

        if (rowId > 0) { // редактирование
            databaseConnector.editOrdersRow(rowId, address, name, quantity);
            rowId = 0;
        } else { // добавление
            databaseConnector.insertOrdersRow(address, name, quantity);
        }
        refreshScreen();

        // вывод сообщения на экран о выполненном действии
        Toast.makeText(this, "Order updated", Toast.LENGTH_SHORT).show();
    }


    final int MENU_CONTEXT_DELETE_ID = 123;
    final int MENU_CONTEXT_EDIT_ID = 124;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.lv_orders) {
            ListView lv = (ListView) v;
            // создание двух пунктов контекстного меню: удаление и редактирование элемента
            menu.add(Menu.NONE, MENU_CONTEXT_DELETE_ID, Menu.NONE, "Remove item");
            menu.add(Menu.NONE, MENU_CONTEXT_EDIT_ID, Menu.NONE, "Edit item");
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String str = lst.get(info.position);

        switch (item.getItemId()) {
            case MENU_CONTEXT_DELETE_ID: { // удаление элемента из БД
                Log.d(TAG, "removing item pos=" + info.position); // запись информации в журнал

                long rid = Long.parseLong(str.split(",")[0].substring(3)); // получение ID строки
                DatabaseConnector databaseConnector = new DatabaseConnector(this);
                databaseConnector.deleteOrdersRow(rid);

                refreshScreen();
                Toast.makeText(this, "Item removed", Toast.LENGTH_SHORT).show();
                return true;
            }
            case MENU_CONTEXT_EDIT_ID: { // редактирование компонента БД
                Log.d(TAG, "edit item pos=" + info.position); // запись информации в журнал

                String address = str.split(", ")[1];
                ((EditText) findViewById(R.id.et_address)).setText(address); // отображение поля адреса

                String name = str.split(", ")[2];
                ((EditText) findViewById(R.id.et_name)).setText(name); // отображение поля названия

                String quantity = str.split(", ")[3];
                ((EditText) findViewById(R.id.et_quantity)).setText(quantity); // отображение поля количества

                rowId = Long.parseLong(str.split(",")[0].substring(3)); // получение ID элемента

                return true;
            }
            default:
                return super.onContextItemSelected(item);
        }
    }


    public void updateList(ArrayList<String> lst) {
        this.lst = lst;
        ListAdapter listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, lst);

        ListView lv = findViewById(R.id.lv_orders);
        lv.setAdapter(listAdapter);
    }
}