package com.mak.studytracker.activities.input;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mak.studytracker.R;
import com.mak.studytracker.activities.progress.ProgressActivity;
import com.mak.studytracker.helpers.DatabaseHandler;
import com.mak.studytracker.models.Subject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddSubjectActivity extends AppCompatActivity {

    private static final String TAG = "addsubjectkhuddus";
    private Button submitButton;
    private DatePicker targetDate;
    private EditText subjectNameEditText;
    private RadioGroup subjectUnitRadio;
    private RadioButton radioLessons;
    private RadioButton radioQuestions;
    private RadioButton radioPages;
    private RadioButton selectedUnit;
    private EditText unitsValueEditText;
    private DatabaseHandler databaseHandler;
    private CheckBox sunday;
    private CheckBox monday;
    private CheckBox tuesday;
    private CheckBox wednesday;
    private CheckBox thursday;
    private CheckBox friday;
    private CheckBox saturday;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        targetDate = (DatePicker) findViewById(R.id.target_date);
        submitButton = (Button) findViewById(R.id.button_submit);
        subjectNameEditText = (EditText) findViewById(R.id.subject_name);
        subjectUnitRadio = (RadioGroup) findViewById(R.id.subject_unit);
        radioPages = (RadioButton) findViewById(R.id.radioPages);
        radioQuestions = (RadioButton) findViewById(R.id.radioQuestions);
        radioLessons = (RadioButton) findViewById(R.id.radioLessons);
        unitsValueEditText = (EditText) findViewById(R.id.units_value);
        sunday=(CheckBox)findViewById(R.id.sunday);
        monday=(CheckBox)findViewById(R.id.monday);
        tuesday=(CheckBox)findViewById(R.id.tuesday);
        wednesday=(CheckBox)findViewById(R.id.wednesday);
        thursday=(CheckBox)findViewById(R.id.thursday);
        friday=(CheckBox)findViewById(R.id.friday);
        saturday=(CheckBox)findViewById(R.id.saturday);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = subjectUnitRadio.getCheckedRadioButtonId();
                selectedUnit = (RadioButton) findViewById(selectedId);
                Toast.makeText(AddSubjectActivity.this, selectedUnit.getText(), Toast.LENGTH_SHORT).show();

                // get the values for day of month , month and year from a date picker
                String day = "Day = " + targetDate.getDayOfMonth();
                String month = "Month = " + (targetDate.getMonth() + 1);
                String year = "Year = " + targetDate.getYear();
                // display the values by using a toast
                databaseHandler = new DatabaseHandler(AddSubjectActivity.this);
                databaseHandler.addSubject(new Subject(
                        1,
                        subjectNameEditText.getText().toString(),
                        selectedUnit.getText().toString(),
                        Integer.parseInt(unitsValueEditText.getText().toString()),
                        new SimpleDateFormat("dd-MM-yyyy").format(new Date()).toString(),
                        Subject.combineDate(targetDate.getDayOfMonth(),targetDate.getMonth(),targetDate.getYear()),
                        Subject.buildSelectedDays(
                                sunday.isChecked(),
                                monday.isChecked(),
                                tuesday.isChecked(),
                                wednesday.isChecked(),
                                thursday.isChecked(),
                                friday.isChecked(),
                                saturday.isChecked()
                        ),
                        6
                        ));
                onBackPressed();
//                Intent intent=new Intent(AddSubjectActivity.this,ProgressActivity.class);
                Toast.makeText(getApplicationContext(), day + "\n" + month + "\n" + year, Toast.LENGTH_LONG).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
