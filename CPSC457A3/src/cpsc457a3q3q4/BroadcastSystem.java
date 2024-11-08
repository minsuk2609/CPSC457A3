package cpsc457a3q3q4;


import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

public class BroadcastSystem{
	//Map of agents, with their own copy of messages
//    private Map<BroadcastAgent, ConcurrentLinkedQueue<String>> agents;
    
    private ArrayList<BroadcastAgent> agents;

    public BroadcastSystem() {
    	this.agents = new ArrayList<>();
    }
    
    public void addAgent(BroadcastAgent agent) {
    	agents.add(agent);
    }
    
//    public void broadcast(String message) {
//        for (ConcurrentLinkedQueue<String> queue : agents.values()) {
//            queue.add(message);
//        }
//    }

//    public String receive(BroadcastAgent agent) {
//    	ConcurrentLinkedQueue<String> queue = agents.get(agent);
//        return queue.poll();
//    }
    
    public ArrayList<BroadcastAgent> getAgents() {
    	return this.agents;
    }
	
}

