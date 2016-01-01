import org.junit.Test;

import java.io.IOException;

/**
 * Created by hagai_lvi on 01/01/2016.
 */
public class TwitsIteratorTest {

	@Test
	public void test() throws IOException {
		TwitsIterator t = new TwitsIterator("twits.txt", 2);

		String twit;
		while ((twit = t.getNext()) != null){
			System.out.println(twit);
		}
	}

}