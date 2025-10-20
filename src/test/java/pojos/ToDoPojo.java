package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//This will ignore the properties that does not exist in this class while converting response into this class object.
public class ToDoPojo {

    //1st: Private Variables
    private Integer userId;
    private String title;
    private Boolean completed;

    //2nd: Constructors
    //This is necessary for the de-serialization
    public ToDoPojo() {
    }

    public ToDoPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    //3rd: Public Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    //4th: toString() method -Optional
    @Override
    public String toString() {
        return "ToDoPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
