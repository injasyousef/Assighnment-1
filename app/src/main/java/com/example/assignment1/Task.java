package com.example.assignment1;

public class Task {
    private String name;
    private String author;
    private String status;
    public Task(){

    }

    public Task(String name, String author, String status) {
        this.name = name;
        this.author = author;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                '}'+"\n";
    }

}
