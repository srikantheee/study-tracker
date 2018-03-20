package com.mak.studytracker.activities.schedule;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mak.studytracker.R;
import com.mak.studytracker.helpers.DatabaseHandler;
import com.mak.studytracker.models.Subject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class ScheduleActivity extends AppCompatActivity {
    private static final int MY_CAL_WRITE_REQ = 1;
    private static final int MY_CAL_REQ = 1;
    private DatabaseHandler databaseHandler;
    private Subject subject;
    private TextView subjectHeading;
    private TextView subjectContent;
    private Button updateProgressButton;
    private EditText result;
    private LinearLayout container;
    private Button calendarEvents;
    private TextView subjectWarning;

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
        subjectWarning =(TextView)findViewById(R.id.subject_warning);
        updateProgressButton = (Button) findViewById(R.id.update_progress);
//        result = (EditText) findViewById(R.id.editTextResult);
        container=(LinearLayout)findViewById(R.id.container);
        calendarEvents=(Button)findViewById(R.id.calendar_events);
        calendarEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra(CalendarContract.Events.TITLE, "Study "+subject.getName()+" ");
//                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Home suit home");
                intent.putExtra(CalendarContract.Events.DESCRIPTION, "Study "+subject.getName()+" : Study Tracker app event ");

// Setting dates
                GregorianCalendar beginDate = new GregorianCalendar(
                        Subject.getYear(subject.getBeginDate()),
                        Subject.getMonth(subject.getBeginDate()),
                        Subject.getDay(subject.getBeginDate())
                );
                GregorianCalendar targetDate = new GregorianCalendar(
                        Subject.getYear(subject.getTargetDate()),
                        Subject.getMonth(subject.getTargetDate()),
                        Subject.getDay(subject.getTargetDate())
                );
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        beginDate.getTimeInMillis());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                        targetDate.getTimeInMillis());

// make it a full day event
//                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

// make it a recurring Event
                intent.putExtra(CalendarContract.Events.RRULE, "FREQ=WEEKLY;BYDAY="+subject.getRuleSelectedDaysOutput()+"");

// Making it private and shown as busy
                intent.putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
                intent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);

                startActivity(intent);
//                addCalendar();
//                getDataFromCalendarTable(container);
            }
        });
        updateProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(ScheduleActivity.this);
                View promptsView = li.inflate(R.layout.prompt_update_progress, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        ScheduleActivity.this);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextFinishedUnits);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        databaseHandler.updateSubjectProgress(subject,Integer.parseInt(userInput.getText().toString()));
//                                        result.setText(userInput.getText());
                                        subject = databaseHandler.getSubject(getIntent().getIntExtra("subject_id",0));
                           loadData();
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


            }
        });
loadData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void loadData(){
        subjectHeading.setText(subject.getName());

        subjectContent.setText(Html.fromHtml(
                "<b><big> Target Date :</big></b> " + subject.getTargetDate()
                        + "<br>Begining Date : " +(subject.getBeginDate())
                        + "<br>Total Units : " +(subject.getUnitsValue())
                        + "<br>Completed Units : " +(  subject.getCurrentProgress())
                        + "<br>Estimated Completion : " +(  subject.estimatedCompletion())
                        + "<br>Remaining Units : " +(subject.getUnitsValue()- subject.getCurrentProgress())
                        + "<br>Selected Days : " +(subject.getSelectedDaysOutput())
                        + "<br>Total no. of weeks to study : " +(Subject.getNoOfWeeks(subject.getBeginDate(),subject.getTargetDate()))
                        + "<br>Remaining no. of weeks to study : " +(Subject.getNoOfWeeks( new SimpleDateFormat("dd-MM-yyyy").format(new Date()).toString(),subject.getTargetDate()))
                        + "<br>Pages per each sitting : " +(subject.unitsPerEachSitting())
        ));
        if (subject.getCurrentProgress()>=subject.estimatedCompletion()){
            subjectWarning.setText(Html.fromHtml(
                    " Well done. You're going ahead of "
                            +(subject.getCurrentProgress()-subject.estimatedCompletion())
                            +" pages."
            ));


        }
        else{
            subjectWarning.setText(Html.fromHtml(
                    " You need to improve. You're lagging "
                            +(subject.estimatedCompletion()-subject.getCurrentProgress())
                            +" pages."
            ));

        }

    }
}
