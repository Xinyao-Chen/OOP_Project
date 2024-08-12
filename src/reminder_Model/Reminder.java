package reminder_Model;

import java.util.Date;

public class Reminder {
    private String name;
    private Date date;
    private String category;
    private String status;

    public Reminder(String name, Date date, String category, String status) {
        this.name = name;
        this.date = date;
        this.category = category;
        this.status = status;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return name + " (" + date + ") - " + category + " - " + status;
    }
}
