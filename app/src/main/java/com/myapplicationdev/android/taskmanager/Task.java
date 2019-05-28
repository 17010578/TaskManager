package com.myapplicationdev.android.taskmanager;

public class Task {
    private int id;
    private String description;
    private String name;
    private int seconds;




    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getSeconds () {
        return seconds;
    }

    public Task(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
//        this.seconds = seconds;
    }
    @Override
    public String toString() {
        return  id + " " + name + "\n" + description;

    }
}
