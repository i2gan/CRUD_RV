package com.abc.crudrv1205;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHelper myDb;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DBHelper(this);

        recyclerView = findViewById(R.id.rv);

        addData();

        List<Item> items = new ArrayList<Item>();

        LinkedList<Data> list = myDb.getAll();
        for (Data d: list) {
            items.add(new Item(d.name, d.year));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));

    }

    public void addData() {
        String name = "cccc";
        int year = 1981;

        Data data = new Data(name, year);
        myDb.addOne(data);
    }
}