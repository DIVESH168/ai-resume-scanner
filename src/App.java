public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("=== AI Resume Screener ===");

        int candidateId = 1;
        int jobId = 1;

        // Job Description
        String jobDescription =
            "We need a Java Developer Intern who knows " +
            "Core Java, OOP, DSA, MySQL, JDBC, SQL, " +
            "Spring Boot, REST APIs, Git, GitHub, " +
            "Problem Solving and Data Structures.";

        // ← Your PDF resume path on Desktop
        String resumePath = "C:\\Users\\acer\\Divesh_P_Resume.pdf";
;

        // Read PDF
        System.out.println("Reading your PDF resume...");
        String resumeText = PDFReader.readPDF(resumePath);

        if (resumeText == null) {
            System.out.println("❌ Could not read resume!");
            return;
        }

        // Send to AI
        System.out.println("\nSending to Groq AI for screening...");
        String aiResponse = AIScreener.screenResume(jobDescription, resumeText);

        // Extract result
        String aiContent = extractContent(aiResponse);
        System.out.println("\n=== AI Analysis ===");
        System.out.println(aiContent);

        // Parse fields
        int score = ResultExtractor.extractScore(aiContent);
        String matched = ResultExtractor.extractMatchedSkills(aiContent);
        String missing = ResultExtractor.extractMissingSkills(aiContent);
        String recommendation = ResultExtractor.extractRecommendation(aiContent);

        // Save to DB
        ResultSaver.saveResult(candidateId, jobId,
                               score, matched, missing, recommendation);

        // Final Report
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║   YOUR SCREENING REPORT      ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ Candidate : Divesh P         ║");
        System.out.println("║ Job       : Java Dev Intern  ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("  Score     : " + score + "/100");
        System.out.println("  Matched   : " + matched);
        System.out.println("  Missing   : " + missing);
        System.out.println("  Verdict   : " + recommendation);
        System.out.println("╚══════════════════════════════╝");
    }

    private static String extractContent(String jsonResponse) {
        try {
            int start = jsonResponse.indexOf("\"content\":\"") + 11;
            int end = jsonResponse.indexOf("\"}", start);
            String content = jsonResponse.substring(start, end);
            content = content.replace("\\n", "\n");
            return content;
        } catch (Exception e) {
            return jsonResponse;
        }
    }
}