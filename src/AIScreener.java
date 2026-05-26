import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AIScreener {

    private static final String API_KEY = "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";
    private static final String API_URL =
        "https://api.groq.com/openai/v1/chat/completions";

    public static String screenResume(String jobDescription, String resumeText) {

        try {
            String prompt =
    "You are a resume screening assistant. " +
    "Candidate Resume: " + resumeText +
    " Instructions: " +
    " 1. First detect the job role from the resume. " +
    " 2. Score the candidate out of 100 based on their OWN target role. " +
    " 3. Match their skills to their target role requirements. " +
    " Respond in this exact format: " +
    "DETECTED ROLE: (role found in resume) " +
    "SCORE: (number out of 100) " +
    "MATCHED SKILLS: (comma separated) " +
    "MISSING SKILLS: (comma separated) " +
    "RECOMMENDATION: (one line summary)";

            String cleanPrompt = prompt
    .replace("\r", " ")
    .replace("\n", " ")
    .replace("\"", "'")
    .replace("\\", " ");

String requestBody =
    "{" +
    "\"model\": \"llama-3.3-70b-versatile\"," +
    "\"messages\": [{" +
    "\"role\": \"user\"," +
    "\"content\": \"" + cleanPrompt + "\"" +
    "}]" +
    "}";
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

            HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
            );

            System.out.println("✅ AI Response received!");
            return response.body();

        } catch (Exception e) {
            System.out.println("❌ AI Error: " + e.getMessage());
            return null;
        }
    }
}





             //   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