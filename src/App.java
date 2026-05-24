public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("=== Resume Screener Starting ===");

        // Job and candidate details
        int candidateId = 1;
        int jobId = 1;

        String jobDescription =
            "We need a Java Developer who knows " +
            "Java, MySQL, REST APIs, Git, Spring Boot.";

        String resumeText =
            "I am Divesh, a Java developer. " +
            "I know Java, MySQL, Data Structures, " +
            "Arrays, HashMap, Sorting Algorithms. " +
            "I have built 2 projects using Java.";

        // Step 1 - Call AI
        System.out.println("Sending resume to AI...");
        String aiResponse = AIScreener.screenResume(jobDescription, resumeText);

        // Step 2 - Extract AI content text
        String aiContent = extractContent(aiResponse);
        System.out.println("\n=== AI Result ===");
        System.out.println(aiContent);

        // Step 3 - Extract fields
        int score = ResultExtractor.extractScore(aiContent);
        String matched = ResultExtractor.extractMatchedSkills(aiContent);
        String missing = ResultExtractor.extractMissingSkills(aiContent);
        String recommendation = ResultExtractor.extractRecommendation(aiContent);

        // Step 4 - Save to database
        System.out.println("\n=== Saving to Database ===");
        ResultSaver.saveResult(candidateId, jobId,
                               score, matched, missing, recommendation);

        // Step 5 - Print final report
        System.out.println("\n=== Final Screening Report ===");
        System.out.println("Candidate ID : " + candidateId);
        System.out.println("Job ID       : " + jobId);
        System.out.println("Score        : " + score + "/100");
        System.out.println("Matched      : " + matched);
        System.out.println("Missing      : " + missing);
        System.out.println("Verdict      : " + recommendation);
        System.out.println("==============================");
    }

    // Extract text content from Groq JSON response
    private static String extractContent(String jsonResponse) {
        try {
            int start = jsonResponse.indexOf("\"content\":\"") + 11;
            int end = jsonResponse.indexOf("\"}", start);
            String content = jsonResponse.substring(start, end);
            // Convert \n to real newlines
            content = content.replace("\\n", "\n");
            return content;
        } catch (Exception e) {
            return jsonResponse;
        }
    }
}