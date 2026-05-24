import java.sql.Connection;
import java.sql.PreparedStatement;

public class DataInserter {

    public static void insertSampleData() {
        Connection conn = DatabaseConnection.getConnection();

        try {
            // Insert sample Job
            PreparedStatement jobStmt = conn.prepareStatement(
                "INSERT INTO jobs (title, description) VALUES (?, ?)"
            );
            jobStmt.setString(1, "Java Developer Intern");
            jobStmt.setString(2,
                "We need a Java Developer who knows " +
                "Java, MySQL, REST APIs, Git, Spring Boot."
            );
            jobStmt.executeUpdate();
            System.out.println("? Sample job inserted!");

            // Insert sample Candidate
            PreparedStatement candStmt = conn.prepareStatement(
                "INSERT INTO candidates (name, email, resume_text) VALUES (?, ?, ?)"
            );
            candStmt.setString(1, "Divesh");
            candStmt.setString(2, "divesh@email.com");
            candStmt.setString(3,
                "I am Divesh, a Java developer. " +
                "I know Java, MySQL, Data Structures, " +
                "Arrays, HashMap, Sorting Algorithms. " +
                "I have built 2 projects using Java."
            );
            candStmt.executeUpdate();
            System.out.println("? Sample candidate inserted!");

            jobStmt.close();
            candStmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("? Error: " + e.getMessage());
        }
    }
}