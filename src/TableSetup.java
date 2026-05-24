import java.sql.Connection;
import java.sql.Statement;

public class TableSetup {

    public static void createTables() {
        Connection conn = DatabaseConnection.getConnection();

        try {
            Statement stmt = conn.createStatement();

            // Table 1 - Jobs
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS jobs (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "title VARCHAR(100), " +
                "description TEXT)"
            );
            System.out.println("? Jobs table created!");

            // Table 2 - Candidates
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS candidates (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(100), " +
                "email VARCHAR(100), " +
                "resume_text TEXT)"
            );
            System.out.println("? Candidates table created!");

            // Table 3 - Screening Results
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS screening_results (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "candidate_id INT, " +
                "job_id INT, " +
                "score INT, " +
                "matched_skills VARCHAR(255), " +
                "missing_skills VARCHAR(255), " +
                "recommendation TEXT, " +
                "FOREIGN KEY (candidate_id) REFERENCES candidates(id), " +
                "FOREIGN KEY (job_id) REFERENCES jobs(id))"
            );
            System.out.println("? Screening results table created!");

            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("? Error: " + e.getMessage());
        }
    }
}