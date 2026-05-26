import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║     AI Resume Screener v3.0      ║");
        System.out.println("╚══════════════════════════════════╝");

        Scanner scanner = new Scanner(System.in);

        // Get candidate name
        System.out.print("\nEnter candidate name: ");
        String candidateName = scanner.nextLine();

        // Get resume path
        System.out.print("Enter resume PDF path: ");
        String resumePath = scanner.nextLine();

        int candidateId = 1;
        int jobId = 1;

        // Read PDF
        System.out.println("\nReading PDF resume...");
        String resumeText = PDFReader.readPDF(resumePath);

        if (resumeText == null) {
            System.out.println("❌ Could not read resume!");
            scanner.close();
            return;
        }

        // Send to AI (no job description needed!)
        System.out.println("AI is detecting role and scoring...");
        String aiResponse = AIScreener.screenResume("", resumeText);

        // Extract result
        String aiContent = extractContent(aiResponse);

        // Parse fields
        String detectedRole = ResultExtractor.extractDetectedRole(aiContent);
        int score = ResultExtractor.extractScore(aiContent);
        String matched = ResultExtractor.extractMatchedSkills(aiContent);
        String missing = ResultExtractor.extractMissingSkills(aiContent);
        String recommendation = ResultExtractor.extractRecommendation(aiContent);

        // Save to DB
        ResultSaver.saveResult(candidateId, jobId,
                               score, matched, missing, recommendation);

        // Final Report
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║        SCREENING REPORT          ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("  Candidate     : " + candidateName);
        System.out.println("  Detected Role : " + detectedRole);
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("  Score         : " + score + "/100");
        System.out.println("  Matched       : " + matched);
        System.out.println("  Missing       : " + missing);
        System.out.println("  Verdict       : " + recommendation);
        System.out.println("╚══════════════════════════════════╝");

        scanner.close();
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