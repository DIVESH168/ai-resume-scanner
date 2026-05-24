public class ResultExtractor {

    public static int extractScore(String aiContent) {
        try {
            // Find "SCORE: 60" and extract 60
            String[] lines = aiContent.split("\n");
            for (String line : lines) {
                if (line.startsWith("SCORE:")) {
                    String numberPart = line.replace("SCORE:", "").trim();
                    // Extract only digits
                    String digits = numberPart.replaceAll("[^0-9]", "");
                    return Integer.parseInt(digits);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Score extract error: " + e.getMessage());
        }
        return 0;
    }

    public static String extractMatchedSkills(String aiContent) {
        return extractField(aiContent, "MATCHED SKILLS:");
    }

    public static String extractMissingSkills(String aiContent) {
        return extractField(aiContent, "MISSING SKILLS:");
    }

    public static String extractRecommendation(String aiContent) {
        return extractField(aiContent, "RECOMMENDATION:");
    }

    private static String extractField(String aiContent, String fieldName) {
        try {
            String[] lines = aiContent.split("\n");
            for (String line : lines) {
                if (line.startsWith(fieldName)) {
                    return line.replace(fieldName, "").trim();
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Extract error: " + e.getMessage());
        }
        return "Not found";
    }
}