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

    public Task(int id, String name, String description, int seconds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.seconds = seconds;
    }
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", seconds=" + seconds +
                '}';
    }
}
