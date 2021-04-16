package com.cindy.latihanuki;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTodo extends AppCompatActivity {
    EditText edtTitle, edtDesc, edtDate;
    Button btnSubmit, btnCancel;
    DatabaseHelper myDB;
    DatePickerDialog.OnDateSetListener date;
    Calendar myCalender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        edtTitle = findViewById(R.id.edtTitle);
        edtDesc = findViewById(R.id.edtDesc);
        edtDate = findViewById(R.id.edtDate);
        btnSubmit = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

        myDB = new DatabaseHelper(this);
        myCalender = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalender.set(Calendar.YEAR, year);
                myCalender.set(Calendar.MONTH, month);
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(); //memanggil fungsi update data


            }
        };
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddTodo.this, date, myCalender
                        .get(Calendar.YEAR), myCalender.get(Calendar.MONTH),
                        myCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtTitle.getText().toString();
                String date = edtDate.getText().toString();
                String desc = edtDesc.getText().toString();
                if (title.equals("") || date.equals("") || desc.equals("")) {
                    if (title.equals("")) {
                        edtTitle.setError("Judul harus di isi");
                    }
                    if (desc.equals("")) {
                        edtDesc.setError("Deskripsi harus di isi");
                    }
                    if (date.equals("")) {
                        edtDate.setError("Tanggal harus di isi");
                    }
                } else {
                    boolean isInserted = myDB.insertData(title, desc, date);
                    if (isInserted) {
                        Toast.makeText(AddTodo.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddTodo.this, "Data gagal ditambahkan", Toast.LENGTH_SHORT).show();
                    }

                    startActivity(new Intent(AddTodo.this, MainActivity.class));
                    finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddTodo.this, MainActivity.class));
                finish();
            }
        });
    }

    //fungsi untuk mengupdate isi edtDate dari value myCalender
    private void updateLabel(){
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.US);
        edtDate.setText(simpleDateFormat.format(myCalender.getTime()));
    }
}