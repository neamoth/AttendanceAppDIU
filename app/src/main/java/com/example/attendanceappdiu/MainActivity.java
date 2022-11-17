package com.example.attendanceappdiu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btnFloat;
    String[] itemsCourse = {"CSE334-Pervasive Computing", "CSE335-Pervasive Computing and Mobile App Development Lab "};
    String[] itemsSection = {"T3_PC_A", "T3_PC_B", "T3_PC_C", "PC_A", "PC_B", "PC_C"};

    AutoCompleteTextView courseSelect;
    AutoCompleteTextView sectionSelect;

    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Find the items
        courseSelect = findViewById(R.id.courseSelect);
        sectionSelect = findViewById(R.id.sectionSelect);
        //Adapting the items
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, itemsCourse);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, itemsSection);
        //Setting the items on adapterItems
        courseSelect.setAdapter(adapterItems);
        sectionSelect.setAdapter(adapterItems);

        btnFloat = findViewById(R.id.btnFloat);
        btnFloat.setOnClickListener(v -> showDialog());
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.class_dialog, null);
        builder.setView(view);
        builder.create().show();
    }
}