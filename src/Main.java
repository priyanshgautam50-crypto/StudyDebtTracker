
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudyTrackerService service = new StudyTrackerService();

        boolean running = true;

        while (running) {

            
        
System.out.println("\n===== Study Debt Tracker =====");
System.out.println("1. Add a study topic");
System.out.println("2. View all study topics");
System.out.println("3. Mark a topic as completed");
System.out.println("4. Calculate study debt");
System.out.println("5. View overdue topics");
System.out.println("6. View study summary");
System.out.println("8. Delete a study topic");
System.out.println("7. Exit");
System.out.print("Enter your choice: ");


            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    System.out.print("Enter topic name: ");
                    String topicName = scanner.nextLine();

                    System.out.print("Enter subject: ");
                    String subject = scanner.nextLine();

                    System.out.print("Enter planned date (YYYY-MM-DD): ");
                    String dateInput = scanner.nextLine();

                    System.out.print("Enter estimated minutes: ");
                    String minutesInput = scanner.nextLine();

                    try {
                        LocalDate plannedDate =
                                LocalDate.parse(dateInput);

                        int estimatedMinutes =
                                Integer.parseInt(minutesInput);

                        if (topicName.isBlank() || subject.isBlank()) {
                            System.out.println(
                                    "Topic name and subject cannot be empty."
                            );
                        } else if (estimatedMinutes <= 0) {
                            System.out.println(
                                    "Estimated minutes must be greater than zero."
                            );
                        } else {
                            service.addTopic(
                                    topicName,
                                    subject,
                                    plannedDate,
                                    estimatedMinutes
                            );
                        }

                    } catch (DateTimeParseException e) {
                        System.out.println(
                                "Invalid date. Use YYYY-MM-DD, for example 2026-10-03."
                        );
                    } catch (NumberFormatException e) {
                        System.out.println(
                                "Please enter minutes as a whole number."
                        );
                    }
                    break;

                case "2":
                    System.out.println("\n--- All Study Topics ---");
                    service.showAllTopics();
                    break;

                case "3":
                    System.out.print("Enter the topic ID to complete: ");
                    String idInput = scanner.nextLine();

                    try {
                        int id = Integer.parseInt(idInput);

                        if (service.markTopicCompleted(id)) {
                            System.out.println(
                                    "Topic marked as completed!"
                            );
                        } else {
                            System.out.println(
                                    "No topic found with that ID."
                            );
                        }

                    } catch (NumberFormatException e) {
                        System.out.println(
                                "Please enter a valid numeric ID."
                        );
                    }
                    break;

                case "4":
                    int debt = service.calculateStudyDebt();

                    System.out.println(
                            "Your study debt is " + debt + " minutes."
                    );
                    System.out.println(
                            "That is approximately "
                            + String.format("%.2f", debt / 60.0)
                            + " hours."
                    );
                    break;

                
                case "5":
                    System.out.println("\n--- Overdue Study Topics ---");
                    service.showOverdueTopics();
                    break;

                
                case "6":
                    service.showStudySummary();
                    break;


                case "8":
                     System.out.print("Enter the ID of the topic to delete: ");
                     int deleteId = scanner.nextInt();
                     scanner.nextLine();

                     service.deleteTopic(deleteId);
                     break;

                case "7":
                    running = false;
                    System.out.println(
                            "Thank you for using Study Debt Tracker!"
                    );
                    break;


                default:
                    System.out.println(
                            "Invalid choice. Please select 1 to 8."
                    );

            }
        }

        scanner.close();
    }
    
}
