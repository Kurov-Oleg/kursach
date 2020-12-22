package models;

public class Point {
    private String date;
    private String finalDate;
    private String description;
    private String status;
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
