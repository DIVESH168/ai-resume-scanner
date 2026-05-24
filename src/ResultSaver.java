import java.sql.Connection;
import java.sql.PreparedStatement;

public class ResultSaver {

    public static void saveResult(int candidateId, int jobId,
                                   int score, String matchedSkills,
                                   String missingSkills, String recommendation) {
        Connection conn = DatabaseConnection.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO screening_results " +
                "(candidate_id, job_id, score, matched_skills, " +
                "missing_skills, recommendation) " +
                "VALUES (?, ?, ?, ?, ?, ?)"
            );

            stmt.setInt(1, candidateId);
            stmt.setInt(2, jobId);
            stmt.setInt(3, score);
            stmt.setString(4, matchedSkills);
            stmt.setString(5, missingSkills);
            stmt.setString(6, recommendation);

            stmt.executeUpdate();
            System.out.println("✅ Result saved to database!");

            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("❌ Save error: " + e.getMessage());
        }
    }
}