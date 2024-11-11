package cpsc457a3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LocalMemory {
	//Making two separate list for turns and flags
	private ArrayList<Integer> turnList;
	private ArrayList<Integer> flagList;
	private volatile Set<Integer> processMap;
	private volatile int counter = 0;
	
	public LocalMemory(ArrayList<Integer> turnList, ArrayList<Integer> flagList) {
		this.turnList = turnList;
		this.flagList = flagList;
		this.processMap = new HashSet<Integer>();
	}
	
	// if turn flag is true, then load from the turn list. Else, load from flag list
	public synchronized int load(int index, boolean turn) {
		if (turn) {
			return turnList.get(index);
		}
		else {
			return flagList.get(index);
		}
	}
	
	// if turn flag is true, then store value at index into turn list. Else, store into flag list
	public synchronized void store(int index, int value, boolean turn) {
		if (turn) {
			turnList.add(index, value);
		} 
		else {
			flagList.add(index, value);
		}
	}
	
	public synchronized void increment(int processId) {
		counter ++;
		processMap.add(processId);
		if(processMap.size() > 1) {
			System.out.println("Process " + processMap + " in critical section");
		}
	}
	
	public synchronized void decrement(int processId) {
		counter --;
		processMap.remove(processId);
	}
}