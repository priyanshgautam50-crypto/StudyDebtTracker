import java.time.LocalDate;
import java.util.List;

public class StudyTrackerService {

    private final StudyTopicDAO topicDAO = new StudyTopicDAO();
    private List<StudyTopic> topics;

    // Load saved topics from MySQL when the service is created
    public StudyTrackerService() {
        topics = topicDAO.getAllTopics();
        System.out.println("Loaded " + topics.size()
                + " study topic(s) from MySQL.");
    }

    // Add a new study topic
    public void addTopic(String topicName, String subject,
                         LocalDate plannedDate, int estimatedMinutes) {

        // ID 0 is temporary; MySQL generates the real ID
        StudyTopic topic = new StudyTopic(
                0, topicName, subject, plannedDate, estimatedMinutes
        );

        int generatedId = topicDAO.addTopic(topic);

        if (generatedId != -1) {
            topics.add(new StudyTopic(
                    generatedId, topicName, subject,
                    plannedDate, estimatedMinutes
            ));
            System.out.println("Study topic added successfully!");
        } else {
            System.out.println("Topic was not saved. Please try again.");
        }
    }

    // Display all study topics
    public void showAllTopics() {
        if (topics.isEmpty()) {
            System.out.println("No study topics found.");
            return;
        }

        for (StudyTopic topic : topics) {
            System.out.println(topic);
        }
    }

      // Mark a topic completed and save the change to MySQL
    public boolean markTopicCompleted(int id) {
        for (StudyTopic topic : topics) {
            if (topic.getId() == id) {

                boolean updated = topicDAO.markTopicCompleted(id);

                if (updated) {
                    topic.markCompleted();
                    System.out.println("Topic marked completed and saved to MySQL!");
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    // Calculate total unfinished study time
    public int calculateStudyDebt() {
        int totalMinutes = 0;

        for (StudyTopic topic : topics) {
            if (!topic.isCompleted()) {
                totalMinutes += topic.getEstimatedMinutes();
            }
        }

        return totalMinutes;
    }

    // Display overdue topics
    public void showOverdueTopics() {
        LocalDate today = LocalDate.now();
        boolean found = false;

        for (StudyTopic topic : topics) {
            if (!topic.isCompleted()
                    && topic.getPlannedDate().isBefore(today)) {
                System.out.println(topic);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No overdue study topics. Great job!");
        }
    }

    // Display a summary of study progress
    public void showStudySummary() {
        int totalTopics = topics.size();
        int completedTopics = 0;
        int incompleteTopics = 0;
        int unfinishedMinutes = 0;
        int overdueTopics = 0;

        LocalDate today = LocalDate.now();

        for (StudyTopic topic : topics) {
            if (topic.isCompleted()) {
                completedTopics++;
            } else {
                incompleteTopics++;
                unfinishedMinutes += topic.getEstimatedMinutes();

                if (topic.getPlannedDate().isBefore(today)) {
                    overdueTopics++;
                }
            }
        }

        System.out.println("\n===== Study Summary =====");
        System.out.println("Total topics: " + totalTopics);
        System.out.println("Completed topics: " + completedTopics);
        System.out.println("Incomplete topics: " + incompleteTopics);
        System.out.println("Unfinished study time: "
                + unfinishedMinutes + " minutes");
        System.out.println("Overdue topics: " + overdueTopics);
    }

    // Delete a study topic from MySQL and the local list
    public boolean deleteTopic(int id) {
        for (StudyTopic topic : topics) {
            if (topic.getId() == id) {

                boolean deleted = topicDAO.deleteTopic(id);

                if (deleted) {
                    topics.remove(topic);
                    System.out.println("Study topic deleted successfully!");
                    return true;
                }

                System.out.println("Could not delete the study topic.");
                return false;
            }
        }

        System.out.println("No study topic found with ID: " + id);
        return false;
    }

}