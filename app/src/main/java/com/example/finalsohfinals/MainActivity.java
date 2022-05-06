package com.example.finalsohfinals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ListView my_list;
    SQLiteDatabase sql_db2;
    String data;

    ArrayList<String> exams;
    ArrayAdapter<String> items_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_list = (ListView) findViewById(R.id.finals_sch);


        try{

            //Insert Data into local DB.
//            sql_db2 = this.openOrCreateDatabase("finalExam_db", MODE_PRIVATE, null);
//            sql_db2.execSQL("CREATE Table IF NOT EXISTS exams (exam_name VARCHAR, exam_date VARCHAR, resource VARCHAR)");
//            sql_db2.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Mobile Computing', '05/05/2022', 'https://developer.android.com/')");
//            sql_db2.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Algorithms & Data Structures', '07/05/2022', 'https://www.geeksforgeeks.org/')");
//            sql_db2.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Intro. to Scripting', '08/05/2022', 'https://linux.die.net/man/')");
//            sql_db2.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Software Engineering', '08/05/2022', 'https://www.tutorialspoint.com/software_engineering/index.htm')");
//            sql_db2.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Probability and Statistics', '09/05/2022', 'https://www.khanacademy.org/math/statistics-probability')");

            //Cursor to select in DB.
            Cursor c = sql_db2.rawQuery("Select * from exams", null);

            //Index columns
            int ex_name_index = c.getColumnIndex("exam_name");
            int ex_date_index = c.getColumnIndex("exam_date");
            int resource = c.getColumnIndex("resource");

            //Moves to first to be at first row in DB
            c.moveToFirst();

            exams = new ArrayList<String>();
            while(c!=null){
                data = "Exam name:" + c.getString(ex_name_index) + "\nExam Date: " + c.getString(ex_date_index) + "\nExam Resource: " + c.getString(resource);
                exams.add(data);
                c.moveToNext();
            }
            items_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exams);
            my_list.setAdapter(items_adapter);
    
            Log.e("all items are: ", exams.toString());

            my_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getApplicationContext(), WebView.class);
                    startActivity(intent);
                }
            });



        }

        catch(Exception e){
            e.printStackTrace();
        }


    }



    String text;
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        text = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}