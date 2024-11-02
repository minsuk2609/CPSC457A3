package cpsc457a3;

import java.util.HashMap;
import java.util.Map;

public class LocalMemory {
	private Map<Integer, String> LocMemory = new HashMap<>();
	
	public synchronized String load(int x) {
		return LocMemory.get(x);
	}
	
	public synchronized void store(int x, String v) {
	}
}