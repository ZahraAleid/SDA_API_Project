package pojos;

public class ActivitiesPojo {

    protected Integer id;
    protected  String title;
    protected  String dueDate;
    protected  Boolean completed;
    /*
    "id": 1,
    "title": "Activity 1",
    "dueDate": "2025-10-20T14:23:28.3865336+00:00",
    "completed": false
     */

    public ActivitiesPojo() {
    }

    public ActivitiesPojo(Integer id, String title, String dueDate, Boolean completed) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "ActivitiesPojo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", completed=" + completed +
                '}';
    }
}
