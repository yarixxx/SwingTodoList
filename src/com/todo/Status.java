package com.todo;

public enum Status {
    WAITING ("Waiting"),
    DONE ("Done");

    private final String title;

    Status(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}