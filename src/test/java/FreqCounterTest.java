import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
/**
 * Created by hagai_lvi on 01/01/2016.
 */
public class FreqCounterTest {


	@Test
	public void testBasic() throws Exception {
		FreqCounter fc = new FreqCounter();
		assertEquals(0, fc.getFreq("a"));

		fc.increment("a");
		assertEquals(1, fc.getFreq("a"));

		fc.increment("a");
		assertEquals(2, fc.getFreq("a"));

	}


	@Test
	public void testGetFreq() throws Exception {
		FreqCounter fc = new FreqCounter();
		List<Map.Entry<String, Integer>> expected = new LinkedList<>();

		for (int i = 0 ; i < 10 ; i++){
			for (int j = 0 ; j < i ; j++){
				System.out.println(i);
				fc.increment("" + i);
			}
		}

		for (int i = 7 ; i < 10 ; i++) {
			expected.add(new AbstractMap.SimpleEntry<String, Integer>("" + i, i));
		}
		Collections.reverse(expected);

		assertEquals(expected, fc.getTop(3));
	}
}