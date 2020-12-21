public class Point {
    private String date;
    private String final_date;
    private String description;
    private String status;
    public Point() {}

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFinal_date(String final_date) {
        this.final_date = final_date;
    }

   /* public void setTopic(String topic) {
        this.topic = topic;
    }*/

    public String getDate() {
        return date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public String getFinal_date() {
        return final_date;
    }
   /* public String getTopic() {
        return topic;
    }*/
    public String getStatus() {
        return status;
    }
}
