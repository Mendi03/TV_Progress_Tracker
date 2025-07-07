package com.tv_tracker.dao;

public class Show {

    private int show_id;
    private String title;
    private String description;
    private int status;
    
    public Show(int show_id, String title, String description, int status) {
        this.show_id = show_id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Show(int show_id, String title, String description) {
        this.show_id = show_id;
        this.title = title;
        this.description = description;
        this.status = 0;
    }

    public void setShow_id(int show_id) {
        this.show_id = show_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getShow_id() {
        return show_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Show [show_id=" + show_id + ", title=" + title + ", description=" + description + ", status=" + status
                + "]";
    }

    

}
