package com.example.sqliteapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editmeasure, editdate;
    Button btnAddData;
    Button btnviewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editmeasure = (EditText) findViewById(R.id.editText3);
        editdate = (EditText) findViewById(R.id.editText4);
        btnAddData = (Button) findViewById(R.id.button_add);
        btnviewAll = (Button) findViewById(R.id.button);
        AddData();
        viewAll();

    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editmeasure.getText().toString(),
                                editdate.getText().toString());

                        if (isInserted = true)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else {
                            Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                        }

                    }

                }

        );
    }

    public void viewAll()   {
        btnviewAll.setOnClickListener(
                new View.OnClickListener()  {
                    @Override
                    public void onClick (View v)    {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0)
                        {
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()) {
                            buffer.append("Measurement :" + res.getString(0) + "\n");
                            buffer.append("Date :" + res.getString(1) + "\n");

                        }
                        //Show all data;
                        showMessage("Data", buffer.toString());
                    }
                }

        );
    }

    public void showMessage(String title, String Message)   {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
}
