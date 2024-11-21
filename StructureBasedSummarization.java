import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
public class StructureBasedSummarization {
    public static void main(String[] args) throws IOException {
        // Load sentence detection model
        InputStream sentenceModelIn = new FileInputStream("/Users/k.mokshithareddy/Desktop/Java project/en/en-sent (1).bin");
        SentenceModel sentenceModel = new SentenceModel(sentenceModelIn);
        SentenceDetectorME sentenceDetector = new SentenceDetectorME(sentenceModel);

        // Load the "opennlp-en-ud-ewt-pos-1.0-1.9.3.bin" model for part-of-speech tagging
        InputStream posModelIn = new FileInputStream("/Users/k.mokshithareddy/Desktop/Java project/en/opennlp-en-ud-ewt-pos-1.0-1.9.3.bin");
        POSModel posModel = new POSModel(posModelIn);
        POSTaggerME posTagger = new POSTaggerME(posModel);

        // Input text
        String text = "Water, a substance composed of the chemical elements hydrogen and oxygen and existing in gaseous, liquid, and solid states. It is one of the most plentiful and essential of compounds. A tasteless and odourless liquid at room temperature, it has the important ability to dissolve many other substances. Indeed, the versatility of water as a solvent is essential to living organisms. Life is believed to have originated in the aqueous solutions of the world’s oceans, and living organisms depend on aqueous solutions, such as blood and digestive juices, for biological processes. Water also exists on other planets and moons both within and beyond the solar system. In small quantities water appears colourless, but water actually has an intrinsic blue colour caused by slight absorption of light at red wavelengths.\n" +
                "\n Although the molecules of water are simple in structure (H2O), the physical and chemical properties of the compound are extraordinarily complicated, and they are not typical of most substances found on Earth. For example, although the sight of ice cubes floating in a glass of ice water is commonplace, such behaviour is unusual for chemical entities. For almost every other compound, the solid state is denser than the liquid state; thus, the solid would sink to the bottom of the liquid. The fact that ice floats on water is exceedingly important in the natural world, because the ice that forms on ponds and lakes in cold areas of the world acts as an insulating barrier that protects the aquatic life below. If ice were denser than liquid water, ice forming on a pond would sink, thereby exposing more water to the cold temperature. Thus, the pond would eventually freeze throughout, killing all the life-forms present.";

        // Detect sentences in the input text
        String[] sentences = sentenceDetector.sentDetect(text);

        // Define a custom comparator for sentence length
        Comparator<String> sentenceLengthComparator = (s1, s2) -> s2.length() - s1.length();

        // Sort sentences based on their length (structure)
        Arrays.sort(sentences, sentenceLengthComparator);

        // Print the top-ranked sentences (based on structure)
        int numSentencesToIncludeInSummary = 2;
        for (int i = 0; i < Math.min(numSentencesToIncludeInSummary, sentences.length); i++) {
            System.out.println(sentences[i]);
        }
    }
}
