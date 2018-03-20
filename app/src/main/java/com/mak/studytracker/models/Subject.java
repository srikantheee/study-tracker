package com.mak.studytracker.models;

/**
 * Created by itdev-8 on 4/3/16.
 */
public class Subject {
    private   String name;
    private String kind;
    private int id;

    private String unitsName;
    private int unitsValue;
    private String beginDate;
    private String targetDate;
    private String selectedDays;
    private String[] days={"Sun","Mon","Tues","Wed","Thu","Fri","Sat"};
    public String getSelectedDaysOutput() {
        String str[] = selectedDays.split("-");
        String output="| ";
        for (int i=0;i<str.length;i++){
            if(Boolean.parseBoolean(str[i])){
                output=output+days[i]+" | ";
            }
        }
        return output;
    }



    public static   String combineDate(int day, int month, int year){
       return day+"-"+month+"-"+year;

    }
    public static String buildSelectedDays(
            boolean sunday,
            boolean monday,
            boolean tuesday,
            boolean wednesday,
            boolean thursday,
            boolean friday,
            boolean saturday
    ){
        return sunday
                +"-"+monday
                +"-"+tuesday
                +"-"+wednesday
                +"-"+thursday
                +"-"+friday
                +"-"+saturday;
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
