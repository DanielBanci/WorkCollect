package com.example.workcollect.ui.tasks;

import java.time.LocalDate;

public class Task {
    private static final String DEFAULT_ID = "-1";
    private String strId;
    private String strTitle;
    private String strDifficultyLevelKey;
    private LocalDate date;

    public Task(){
        this.strId = DEFAULT_ID;
        this.strTitle = "Default";
        this.strDifficultyLevelKey = "None";
        this.date = LocalDate.now();
    }
    public Task(String strId, String strTitle, String strDifficultyLevelKey, LocalDate date){
        this.strId = strId;
        this.strTitle = strTitle;
        this.strDifficultyLevelKey = strDifficultyLevelKey;
        this.date = date;
    }

    public Integer getPoints(){
        return DifficultyLevelMap.map.get(this.strDifficultyLevelKey);
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public String getStrDifficultyLevelKey() {
        return strDifficultyLevelKey;
    }

    public void setStrDifficultyLevelKey(String strDifficultyLevelKey) {
        this.strDifficultyLevelKey = strDifficultyLevelKey;
    }

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Task{" +
                "strId='" + strId + '\'' +
                ", strTitle='" + strTitle + '\'' +
                ", strDifficultyLevelKey='" + strDifficultyLevelKey + '\'' +
                ", date=" + date +
                '}';
    }
}
