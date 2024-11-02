package cpsc457a3;

import java.util.concurrent.CopyOnWriteArrayList;

public class BroadcastSystem extends Thread{
    private CopyOnWriteArrayList<BroadcastAgent> agents;

    public BroadcastSystem(CopyOnWriteArrayList<BroadcastAgent> agents) {
        this.agents = agents;
    }
    
    // Method to broadcast a message to all agents
    public void broadcast(String message) {
        for (BroadcastAgent agent : agents) {
                agent.receive(message);
        }
    }
    
    // Add an agent to the system
    public void addAgent(BroadcastAgent agent) {
        agents.add(agent);
    }
	
}

