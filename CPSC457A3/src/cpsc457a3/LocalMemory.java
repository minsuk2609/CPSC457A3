package cpsc457a3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LocalMemory {
	//Making two seperate list for turns and flags
	private ArrayList<Integer> turnList;
	private ArrayList<Integer> flagList;
	
	public LocalMemory(ArrayList<Integer> turnList, ArrayList<Integer> flagList) {
		this.turnList = turnList;
		this.flagList = flagList;
	}
	
	public synchronized int load(int index, boolean turn) {
		if (turn) {
			return turnList.get(index);
		}
		else {
			return flagList.get(index);
		}
	}
	
	public synchronized void store(int index, int value, boolean turn) {
		if (turn) {
			turnList.add(index, value);
		} 
		else {
			flagList.add(index, value);
		}
	}
}