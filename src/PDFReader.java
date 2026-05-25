import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.Loader;
import java.io.File;

public class PDFReader {

    public static String readPDF(String filePath) {
        try {
            // PDFBox 3.x uses Loader.loadPDF() instead of PDDocument.load()
            PDDocument document = Loader.loadPDF(new File(filePath));
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            document.close();
            System.out.println("✅ Resume PDF read successfully!");
            System.out.println("--- Extracted Text Preview ---");
            System.out.println(text.substring(0, Math.min(200, text.length())));
            System.out.println("------------------------------");
            return text;
        } catch (Exception e) {
            System.out.println("❌ PDF Error: " + e.getMessage());
            return null;
        }
    }
}