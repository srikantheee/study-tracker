package com.mak.studytracker.models;

import android.net.ParseException;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by itdev-8 on 4/3/16.
 */
public class Subject {
    private String name;
    private String kind;
    private int id;

    private String unitsName;
    private int unitsValue;
    private String beginDate;
    private String targetDate;
    private String selectedDays;
    private String[] days = {"Sun", "Mon", "Tues", "Wed", "Thu", "Fri", "Sat"};
    private String[] ruleDays = {"SU","MO","TU","WE","TH","FR","SA"};

    public String getRuleSelectedDaysOutput() {
        String str[] = selectedDays.split("-");
        String output = "";
        for (int i = 0; i < str.length; i++) {
            if (Boolean.parseBoolean(str[i])) {
                output = output + ruleDays[i] + ",";
            }
        }
        return output;
    }
    public String getSelectedDaysOutput() {
        String str[] = selectedDays.split("-");
        String output = "| ";
        for (int i = 0; i < str.length; i++) {
            if (Boolean.parseBoolean(str[i])) {
                output = output + days[i] + " | ";
            }
        }
        return output;
    }
public int estimatedCompletion(){
        int output=0;
    String str[] = selectedDays.split("-");
    int daysInAWeek = 0;
    for (int i = 0; i < str.length; i++) {
        if (Boolean.parseBoolean(str[i])) {
            daysInAWeek = daysInAWeek + 1;
        }
    }

    output=
                (Subject.getNoOfWeeks(getBeginDate(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()).toString()  )*unitsPerEachSitting()*daysInAWeek);
        return output;
}
    public int unitsPerEachSitting() {
        String str[] = selectedDays.split("-");
        int output=0;
        int daysInAWeek = 0;
        for (int i = 0; i < str.length; i++) {
            if (Boolean.parseBoolean(str[i])) {
                daysInAWeek = daysInAWeek + 1;
            }
        }
        output= (int) Math.ceil(((double)(unitsValue-currentProgress))/(getNoOfWeeks(beginDate,targetDate) * daysInAWeek))   ;


        return output;

    }
    public static int getNoOfWeeks(String begin, String end)
    {

        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        String beginstr[] = begin.split("-");
        String endstr[] = begin.split("-");

Date date=new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date1.clear();
            date1.setTime(sdf.parse(begin) ); // set date 1 (yyyy,mm,dd)
            date2.clear();
            date2.setTime(sdf.parse(end) ); //set date 2 (yyyy,mm,dd)
          } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        long diff = date2.getTimeInMillis() - date1.getTimeInMillis();

        float dayCount = (float) diff / (24 * 60 * 60 * 1000);
        Log.d("Khuddus Date",date1.getTime()+","+date2.getTime()+" days: "+dayCount);

        int week = (int) Math.floor(dayCount / 7);
        return (int)week;
       }

     public static String combineDate(int day, int month, int year) {
        return day + "-" + month + "-" + year;

    }

    public static String buildSelectedDays(
            boolean sunday,
            boolean monday,
            boolean tuesday,
            boolean wednesday,
            boolean thursday,
            boolean friday,
            boolean saturday
    ) {
        return sunday
                + "-" + monday
                + "-" + tuesday
                + "-" + wednesday
                + "-" + thursday
                + "-" + friday
                + "-" + saturday;
    }

    public static int getDay(String Date) {
        String str[] = Date.split("-");
        int day = Integer.parseInt(str[0]);
        return day;
    }

    public static int getMonth(String Date) {
        String str[] = Date.split("-");
        int month = Integer.parseInt(str[1]);
        return month;
    }

    public static int getYear(String Date) {
        String str[] = Date.split("-");
        int year = Integer.parseInt(str[2]);
        return year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }

    private int currentProgress;


    public Subject(

            int id,
            String name,
            String unitsName,
            int unitsValue,
            String beginDate,
            String targetDate,
            String selectedDays,
            int currentProgress
    ) {

        this.id = id;
        this.name = (name);
        this.unitsName = unitsName;
        this.unitsValue = unitsValue;
        this.beginDate = beginDate;
        this.targetDate = targetDate;
        this.selectedDays = selectedDays;
        this.currentProgress = currentProgress;

    }


    public Subject() {

    }


    public String getSelectedDays() {
        return selectedDays;
    }

    public void setSelectedDays(String selectedDays) {
        this.selectedDays = selectedDays;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public int getUnitsValue() {
        return unitsValue;
    }

    public void setUnitsValue(int unitsValue) {
        this.unitsValue = unitsValue;
    }

    public String getUnitsName() {
        return unitsName;
    }

    public void setUnitsName(String unitsName) {
        this.unitsName = unitsName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
