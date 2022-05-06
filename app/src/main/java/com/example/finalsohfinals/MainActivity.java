package com.example.finalsohfinals;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try{

            SQLiteDatabase sql_db = this.openOrCreateDatabase("finalExam_db", MODE_PRIVATE, null);
            sql_db.execSQL("CREATE Table IF NOT EXISTS exams (exam_name VARCHAR, exam_date VARCHAR, resource VARCHAR)");
            sql_db.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Mobile Computing', '05/05/2022', 'https://developer.android.com/')");
            sql_db.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Algorithms & Data Structures', '07/05/2022', 'https://www.geeksforgeeks.org/')");
            sql_db.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Intro. to Scripting', '08/05/2022', 'https://linux.die.net/man/')");
            sql_db.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Software Engineering', '08/05/2022', 'https://www.tutorialspoint.com/software_engineering/index.htm')");
            sql_db.execSQL("INSERT INTO exams (exam_name, exam_date, resource) VALUES ('Probability and Statistics', '09/05/2022', 'https://www.khanacademy.org/math/statistics-probability')");

            //Selects
            Cursor c = sql_db.rawQuery("Select * from exams", null);
            int ex_name_index = c.getColumnIndex("exam_name");
            int ex_date_index = c.getColumnIndex("exam_date");
            int resource = c.getColumnIndex("resource");




        }

        catch(Exception e){
            e.printStackTrace();
        }


    }


}