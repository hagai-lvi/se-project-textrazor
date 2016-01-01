import java.util.*;

/**
 * Created by hagai_lvi on 01/01/2016.
 */
public class FreqCounter {
	HashMap<String, Integer> map = new HashMap<>();

	public int getFreq(String word){
		return map.getOrDefault(word, 0);
	}

	public void increment(String word){
		map.put(word,
				map.getOrDefault(word, 0) + 1);
	}

	public Set<String> getKeys(){
		return map.keySet();
	}

	public List<Map.Entry<String, Integer>> getTop(int n){
		Comparator<Map.Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		};

		List<Map.Entry<String, Integer>> l = new LinkedList(map.entrySet());
		Collections.sort(l, comparator);
		Collections.reverse(l);
		int last = Math.min(n, l.size());
		return l.subList(0, last);
	}
}
