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
    String data;

    ArrayList<String> exams;
    ArrayAdapter<String> items_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_list = (ListView) findViewById(R.id.finals_sch);
        int x =0;

        try{
            //Insert Data into local DB.
            SQLiteDatabase sql_db5 = this.openOrCreateDatabase("finalExam_db", MODE_PRIVATE, null);
            sql_db5.execSQL("CREATE Table IF NOT EXISTS exams (exam_name VARCHAR, exam_date VARCHAR, resource VARCHAR)");
            sql_db5.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Mobile Computing', '05/05/2022', 'https://developer.android.com/')");
            sql_db5.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Algorithms & Data Structures', '07/05/2022', 'https://www.geeksforgeeks.org/')");
            sql_db5.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Intro. to Scripting', '08/05/2022', 'https://linux.die.net/man/')");
            sql_db5.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Software Engineering', '08/05/2022', 'https://www.tutorialspoint.com/software_engineering/index.htm')");
            sql_db5.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Probability and Statistics', '09/05/2022', 'https://www.khanacademy.org/math/statistics-probability')");

            //Cursor to select in DB.
            Cursor c = sql_db5.rawQuery("Select * from exams", null);

            //Index columns
            int ex_name_index = c.getColumnIndex("exam_name");
            int ex_date_index = c.getColumnIndex("exam_date");
            int resource = c.getColumnIndex("resource");

            //Moves to first to be at first row in DB
            c.moveToFirst();
            int counter = 1;

            Log.e("all items are: ", String.valueOf(exams));

            exams = new ArrayList<>();
            items_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exams);
            while(c!=null && counter  <= 5) {
                counter++;
                data = "Course name:\n" + c.getString(ex_name_index) + "\nExam Date:\n" + c.getString(ex_date_index) + "\nExam Resource:\n," + c.getString(resource);
                exams.add(data);
                my_list.setAdapter(items_adapter);
                Log.e("Added", c.getString(ex_name_index));
                Toast.makeText(getApplicationContext(), "Added: " + c.getString(ex_name_index), Toast.LENGTH_SHORT).show();
                c.moveToNext();
            }
            my_list.setAdapter(items_adapter);


            my_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String s = exams.get(i);
                    String[] splitted = s.split(",");
                    String url = splitted[1];
                    Log.e("URL: ", url);

                    Intent intent = new Intent(MainActivity.this, Webpage.class);
                    intent.putExtra("url",url);
                    startActivity(intent);

                }
            });



        }

        catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error in MySQLite", Toast.LENGTH_SHORT).show();
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