package com.mak.studytracker.activities.schedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.TextView;

import com.mak.studytracker.R;
import com.mak.studytracker.helpers.DatabaseHandler;
import com.mak.studytracker.models.Subject;

public class ScheduleActivity extends AppCompatActivity {
    private DatabaseHandler databaseHandler;
    private Subject subject;
    private TextView subjectHeading;
    private TextView subjectContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databaseHandler=new DatabaseHandler(this);
        subject = databaseHandler.getSubject(getIntent().getIntExtra("subject_id",0));
        subjectHeading = (TextView) findViewById(R.id.subject_heading);
        subjectContent =(TextView)findViewById(R.id.subject_content);
        subjectHeading.setText(subject.getName());
        subjectContent.setText(Html.fromHtml(
                "<b><big> Target Date :</big></b> " + subject.getTargetDate()
                        + "<br>Begining Date :</big></b> " +(subject.getBeginDate())
                        + "<br>Total Units :</big></b> " +(subject.getUnitsValue())
                        + "<br>Completed Units :</big></b> " +(  subject.getCurrentProgress())
                        + "<br>Remaining Units :</big></b> " +(subject.getUnitsValue()- subject.getCurrentProgress())
                        + "<br>Selected Days :</big></b> " +(subject.getSelectedDaysOutput())
        ));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
