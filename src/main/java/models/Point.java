package models;

public class Point {
    private String date;
    private String finalDate;
    private String description;
    private String status;
    private String tag;
    public Point() {}

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }

    public String getDate() {
        return date;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public String getStatus() {
        return status;
    }
}
