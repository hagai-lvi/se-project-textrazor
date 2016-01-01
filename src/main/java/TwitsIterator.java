import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;

/**
 * Created by hagai_lvi on 01/01/2016.
 */
public class TwitsIterator {


	private final BufferedReader br;
	private int max = Integer.MAX_VALUE;
	private int count = 0;

	public TwitsIterator(String filePath) throws FileNotFoundException {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		File file = new File(classloader.getResource(filePath).getFile());

		br = new BufferedReader(new FileReader(file));
	}

	public TwitsIterator(String filePath, int max) throws FileNotFoundException {
		this(filePath);
		this.max = max;
	}

	public String getNext() throws IOException {
		String line;

		while ((line = br.readLine()) != null && count < max) {
			if ("".equals(line)){
				continue;
			}
			Object parse = JSONValue.parse(line);

			Object text = ((JSONObject) parse).get("text");
			String resultString = ((String)text).replaceAll("[^\\x00-\\x7F]", "");
			count++;
			return resultString;
		}
		return null;
	}
}
