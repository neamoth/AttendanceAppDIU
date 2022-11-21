package com.example.attendanceappdiu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btnFloat;
    String[] itemsCourse = {"CSE334", "CSE335"};
    String[] itemsSection = {"T3_PC_A", "T3_PC_B", "T3_PC_C", "PC_A", "PC_B", "PC_C"};

    RecyclerView recyclerView;
    ClassAdapter classAdapter;

    AutoCompleteTextView courseSelect;
    AutoCompleteTextView sectionSelect;

    ArrayAdapter<String> courseAdapter, sectionAdapter;

    RecyclerView.LayoutManager layoutManager;
    ArrayList<ClassItem> classItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adapting the items
        courseAdapter = new ArrayAdapter<String>(this, R.layout.list_item, itemsCourse);
        sectionAdapter = new ArrayAdapter<String>(this, R.layout.list_item, itemsSection);

        btnFloat = findViewById(R.id.btnFloat);
        btnFloat.setOnClickListener(v -> showDialog());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        classAdapter = new ClassAdapter(this, classItems);
        recyclerView.setAdapter(classAdapter);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.class_dialog, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        //Find the items
        courseSelect = view.findViewById(R.id.courseSelect);
        sectionSelect = view.findViewById(R.id.sectionSelect);

        // String the items
        courseSelect.setAdapter(courseAdapter);
        sectionSelect.setAdapter(sectionAdapter);

        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnAdd = view.findViewById(R.id.btnAdd);

        btnCancel.setOnClickListener(v -> dialog.dismiss());
        btnAdd.setOnClickListener(v -> {
            addClass();
            dialog.dismiss();
        });
    }

    private void addClass() {
        String CourseCode = courseSelect.getText().toString();
        String CourseSection = sectionSelect.getText().toString();
        classItems.add(new ClassItem(CourseCode, CourseSection));
        classAdapter.notifyDataSetChanged();
    }

}