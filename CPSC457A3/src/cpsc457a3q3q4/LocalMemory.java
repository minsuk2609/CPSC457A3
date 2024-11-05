package cpsc457a3q3q4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LocalMemory {
	//Making two separate list for turns and flags
	private ArrayList<Integer> turnList;
	private ArrayList<Integer> flagList;
	
	public LocalMemory(ArrayList<Integer> turnList, ArrayList<Integer> flagList) {
		this.turnList = turnList;
		this.flagList = flagList;
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
}