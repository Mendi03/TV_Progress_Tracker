package com.tv_tracker.dao;

public class Show {

    private int show_id;
    private String title;
    private String description;
    
    public Show(int show_id, String title, String description) {
        this.show_id = show_id;
        this.title = title;
        this.description = description;
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
    public int getShow_id() {
        return show_id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Show [show_id=" + show_id + ", title=" + title + ", description=" + description + "]\n";
    }

}
