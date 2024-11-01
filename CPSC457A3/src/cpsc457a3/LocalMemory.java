package cpsc457a3;

import java.util.HashMap;
import java.util.Map;

public class LocalMemory {
	private final Map<String, Integer> LocMemory = new HashMap<>();
	
	public synchronized int load(String x) {
		return LocMemory.get(x);
	}
	
	public synchronized int store(String x, int v) {
		return LocMemory.put(x, v);
	}
}