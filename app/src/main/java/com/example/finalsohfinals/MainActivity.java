package com.example.finalsohfinals;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ListView list_view;
    ArrayAdapter<CharSequence> adapter;
    SQLiteDatabase sql_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_view = (ListView) findViewById(R.id.listExams);
        adapter = ArrayAdapter.createFromResource(this, R.array.final_exams, android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.activity_list_item);
        list_view.setAdapter(adapter);
        list_view.setOnItemSelectedListener(this);

        try{

            sql_db = this.openOrCreateDatabase("finalExam_db", MODE_PRIVATE, null);
            sql_db.execSQL("CREATE Table IF NOT EXISTS exams (exam_name VARCHAR, exam_date VARCHAR, resource VARCHAR)");
            sql_db.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Mobile Computing', '05/05/2022', 'https://developer.android.com/')");
            sql_db.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Algorithms & Data Structures', '07/05/2022', 'https://www.geeksforgeeks.org/')");
            sql_db.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Intro. to Scripting', '08/05/2022', 'https://linux.die.net/man/')");
            sql_db.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Software Engineering', '08/05/2022', 'https://www.tutorialspoint.com/software_engineering/index.htm')");
            sql_db.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Probability and Statistics', '09/05/2022', 'https://www.khanacademy.org/math/statistics-probability')");


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

    int counter = 0;
    //Selects


    String data;
    public void onClickButton(View view){
        Cursor c = sql_db.rawQuery("Select * from exams", null);

        int ex_name_index = c.getColumnIndex("exam_name");
        int ex_date_index = c.getColumnIndex("exam_date");
        int resource = c.getColumnIndex("resource");
        //Moves to first to be at first row in DB
        counter++;
        if (counter == 0){
            c.moveToFirst();

        }else if (counter <=5){

            while(c!=null){
                data  = "Exam name:" + c.getString(ex_name_index) + "\nExam Date: " + c.getString(ex_date_index) + "\nExam Resource: " + c.getString(resource);
                adapter.add(data);
                c.moveToNext();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Thank God that's about it.", Toast.LENGTH_SHORT).show();
        }

    }
}