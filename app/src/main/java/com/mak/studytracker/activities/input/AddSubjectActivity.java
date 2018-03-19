package com.mak.studytracker.activities.input;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.mak.studytracker.R;

public class AddSubjectActivity extends AppCompatActivity {

    private static final String TAG = "addsubjectkhuddus" ;
    private Button submitButton;
    private DatePicker targetDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
          targetDate=(DatePicker)findViewById(R.id.target_date);
       submitButton = (Button)findViewById(R.id.button_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the values for day of month , month and year from a date picker
                String day = "Day = " + targetDate.getDayOfMonth();
                String month = "Month = " + (targetDate.getMonth() + 1);
                String year = "Year = " + targetDate.getYear();
                // display the values by using a toast
                Toast.makeText(getApplicationContext(), day + "\n" + month + "\n" + year, Toast.LENGTH_LONG).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
