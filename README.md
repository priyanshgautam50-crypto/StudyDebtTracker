# Study Debt Tracker

A Java and MySQL console application that helps students manage study tasks, track deadlines, and monitor overdue study time.

## Features

- **Add Study Topics:** Record topics with a subject, planned date, and estimated study time.
- **View Study Topics:** Display saved study tasks.
- **Mark as Completed:** Update topics when you finish studying them.
- **Calculate Study Debt:** Calculate pending study time for unfinished topics.
- **View Overdue Topics:** Identify unfinished topics whose planned dates have passed.
- **Study Summary:** View an overview of your study tasks.
- **Delete Topics:** Remove study topics when needed.
- **Persistent Storage:** Store study topics in a MySQL database.

## Technologies Used

- Java
- MySQL
- JDBC (Java Database Connectivity)
- MySQL Connector/J
- Git and GitHub

## Requirements

- Java JDK installed
- MySQL Server
- MySQL Connector/J library
- Git (optional, for version control)

## Database Setup

1. Open MySQL Workbench or another MySQL client.
2. Create the database:

   ```sql
   CREATE DATABASE study_tracker;
   ```

3. Select the database:

   ```sql
   USE study_tracker;
   ```

4. Create the study topics table:

   ```sql
   CREATE TABLE study_topics (
       id INT PRIMARY KEY AUTO_INCREMENT,
       topic_name VARCHAR(100) NOT NULL,
       subject VARCHAR(50) NOT NULL,
       planned_date DATE NOT NULL,
       estimated_minutes INT NOT NULL,
       completed BOOLEAN NOT NULL DEFAULT FALSE
   );
   ```

## Configuration

The application connects to MySQL on `localhost:3306` using the `study_tracker` database.

Set the `STUDY_DB_PASSWORD` environment variable in Windows to your MySQL password. The application reads this variable rather than storing the password directly in the Java source code.

Keep your actual password private. Never commit it to GitHub.

## How to Run

From the project root directory, compile the Java source files:

```powershell
javac -cp "lib/mysql-connector-j-26.7.0.jar" -d out src\*.java
```

Run the application:

```powershell
java -cp "out;lib/mysql-connector-j-26.7.0.jar" Main
```

Make sure MySQL Server is running and the database has been created before launching the application.

## Project Structure

```text
StudyDebtTracker/
├── .gitignore
├── lib/
│   └── mysql-connector-j-26.7.0.jar
├── out/
└── src/
    ├── Main.java
    ├── StudyTopic.java
    ├── StudyTrackerService.java
    ├── StudyTopicDAO.java
    ├── DatabaseConnection.java
    └── DatabaseTest.java
```

## Learning Objectives

This project demonstrates practical use of Java programming, object-oriented design, JDBC database connectivity, SQL operations, and Git-based version control.

## Future Improvements

- Add a graphical user interface.
- Generate weekly study reports.
- Add reminders for upcoming study deadlines.
- Track study progress over time.

## Author

Created as a Java and MySQL learning project.
