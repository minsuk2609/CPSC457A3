package cpsc457a3;


import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.Queue;

public class BroadcastSystem{
	//Map of agents, with their own copy of messages
    private Map<BroadcastAgent, Queue<String>> agents;

    public BroadcastSystem() {
        this.agents = new ConcurrentHashMap<>();
    }
    
    public void addAgent(BroadcastAgent agent) {
    	agents.put(agent, new ConcurrentLinkedQueue<>());
    }
    
    public void broadcast(String message) {
        for (Queue<String> queue : agents.values()) {
            queue.add(message);
        }
    }

    public String receive(BroadcastAgent agent) {
        Queue<String> queue = agents.get(agent);
        return (queue != null) ? queue.poll() : null;
    }
	
}

