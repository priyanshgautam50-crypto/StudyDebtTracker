
import java.time.LocalDate;

public class StudyTopic {

    private int id;
    private String topicName;
    private String subject;
    private LocalDate plannedDate;
    private int estimatedMinutes;
    private boolean completed;

    // Constructor: creates a new study topic
    public StudyTopic(int id, String topicName, String subject,
                      LocalDate plannedDate, int estimatedMinutes) {
        this.id = id;
        this.topicName = topicName;
        this.subject = subject;
        this.plannedDate = plannedDate;
        this.estimatedMinutes = estimatedMinutes;
        this.completed = false;
    }

        // Constructor for loading a topic from MySQL
    public StudyTopic(int id, String topicName, String subject,
                      LocalDate plannedDate, int estimatedMinutes,
                      boolean completed) {
        this.id = id;
        this.topicName = topicName;
        this.subject = subject;
        this.plannedDate = plannedDate;
        this.estimatedMinutes = estimatedMinutes;
        this.completed = completed;
    }

    // Getters: allow other classes to read the fields
    public int getId() {
        return id;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getSubject() {
        return subject;
    }

    public LocalDate getPlannedDate() {
        return plannedDate;
    }

    public int getEstimatedMinutes() {
        return estimatedMinutes;
    }

    public boolean isCompleted() {
        return completed;
    }

    // Marks this topic as completed
    public void markCompleted() {
        this.completed = true;
    }

    // Returns a readable description of the topic
    @Override
    public String toString() {
        return "ID: " + id
                + " | Topic: " + topicName
                + " | Subject: " + subject
                + " | Planned Date: " + plannedDate
                + " | Estimated Minutes: " + estimatedMinutes
                + " | Completed: " + completed;
    }
}
