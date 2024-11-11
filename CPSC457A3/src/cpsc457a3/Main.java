package cpsc457a3;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
    	ArrayList<Integer> turnList = new ArrayList<Integer>();
    	ArrayList<Integer> flagList = new ArrayList<Integer>();
    	int numOfProcs = 50;
    	for (int i = 0; i < numOfProcs; i++) {
    		turnList.add(i, -1);
    	}
    	for (int i = 0; i < numOfProcs - 1; i++) {
    		flagList.add(i, 0);
    	}
    	BroadcastSystem broadcastsystem = new BroadcastSystem();
		LocalMemory mem = new LocalMemory(turnList, flagList);
    	for (int i = 0; i < numOfProcs; i++) {
    		BroadcastAgent agent = new BroadcastAgent(broadcastsystem);
    		DSM dsm = new DSM(mem, agent);
    		Processor procs = new Processor(i, dsm, numOfProcs);
    		new Thread(agent).start();
    		new Thread(dsm).start();
    		new Thread(procs).start();
    	}
    }
}
