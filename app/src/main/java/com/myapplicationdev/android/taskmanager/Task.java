package com.myapplicationdev.android.taskmanager;

public class Task {
    private int id;
    private String description;
    private String name;


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Task(int id, String description, String name) {
        this.id = id;
        this.description = description;
        this.name = name;
    }
}
