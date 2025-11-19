package com.example.workcollect.database;

public enum DatabaseCollections {
    GROUPS("groups"),
    TASKS("tasks");

    private final String strValue;

    DatabaseCollections(String strValue){
        this.strValue = strValue;
    }

    public String getStrValue(){
        return this.strValue;
    }
}
