import com.textrazor.AnalysisException;
import com.textrazor.TextRazor;
import com.textrazor.annotations.AnalyzedText;
import com.textrazor.annotations.Entity;
import org.apache.commons.configuration.ConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by hagai_lvi on 01/01/2016.
 */
public class Main {
	private static String API_KEY;
	private static final int MAX_COUNT = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException, AnalysisException, ConfigurationException {
		API_KEY = new Credentials().getCredentials();

		TextRazor client = new TextRazor(API_KEY);

		client.addExtractor("words");
		client.addExtractor("entities");

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		File file = new File(classloader.getResource("twits.out").getFile());

		TwitsIterator iter = new TwitsIterator("twits.txt", MAX_COUNT);
		FreqCounter fc = new FreqCounter();

		String line;
		int counter = 0;
		while ((line = iter.getNext()) != null ) {

			AnalyzedText response = null;
			try {
				response = client.analyze(line);
			}catch (Exception e){
				e.printStackTrace();
				continue;
			}

			System.out.println(line);
			System.out.println();
			if (response.getResponse().getEntities() != null) {
				for (Entity entity : response.getResponse().getEntities()) {
					System.out.println("Matched Entity: " + entity.getEntityId());
					fc.increment(entity.getEntityId());
				}
			}

			System.out.println("==============================================");

			if (counter % 10 == 0){
				System.out.println("Counter = " + counter);
				printFreq(fc);
			}
			counter++;
		}

		printFreq(fc);

	}

	private static void printFreq(FreqCounter fc) {
		System.out.println("==============================================");
		System.out.println("=================== COUNTS ===================");
		System.out.println("==============================================");


		for (Map.Entry<String, Integer> entry : fc.getTop(20)) {
			System.out.println(entry.getKey() + " : " + entry.getValue() );
		}

		System.out.println("==============================================");
	}
}
