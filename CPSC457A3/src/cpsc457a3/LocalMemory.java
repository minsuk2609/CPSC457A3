package cpsc457a3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LocalMemory {
	//Making two seperate list for turns and flags
	private ArrayList<Integer> turnList = new ArrayList<>();
	private ArrayList<Integer> flagList = new ArrayList<>();
	private Map<Integer, String> LocMemory = new HashMap<>();
	
	public synchronized String load(int x) {
		return LocMemory.get(x);
	}
	
	public synchronized void store(int x, String v) {
	}
}