package com.mak.studytracker.models;

import java.util.ArrayList;

/**
 * Created by itdev-8 on 4/3/16.
 */
public class Subject {
    private   String name;
    private String kind;
    private String id;

    private String unitsName;
    private String unitsValue;
    private String beginDate;
    private String targetDate;
    private String selectedDays;

    public static   String combineDate(int day, int month, int year){
       return day+"-"+month+"-"+year;

    }
    public static int getDay(String Date){
        String str[] = Date.split("-");
        int day = Integer.parseInt(str[0]);
        return day;
    }
    public static int getMonth(String Date){
        String str[] = Date.split("-");
        int month = Integer.parseInt(str[1]);
        return month;
    }
    public static int getYear(String Date){
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

    public String getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(String currentProgress) {
        this.currentProgress = currentProgress;
    }

    private String currentProgress;


    public Subject(

            String id,
            String name,
            String unitsName,
            String unitsValue,
            String beginDate,
            String targetDate,
            String selectedDays,
            String currentProgress
         ) {

        this.id = id;
        this.name =(name);
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

    public String getUnitsValue() {
        return unitsValue;
    }

    public void setUnitsValue(String unitsValue) {
        this.unitsValue = unitsValue;
    }

    public String getUnitsName() {
        return unitsName;
    }

    public void setUnitsName(String unitsName) {
        this.unitsName = unitsName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
