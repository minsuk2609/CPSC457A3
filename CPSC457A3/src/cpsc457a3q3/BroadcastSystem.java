package cpsc457a3q3;

import java.util.ArrayList;

public class BroadcastSystem{
	
	private ArrayList<BroadcastAgent> agents;

    public BroadcastSystem() {
    	this.agents = new ArrayList<>();
    }
    
    public void addAgent(BroadcastAgent agent) {
    	agents.add(agent);
    }
    
    public ArrayList<BroadcastAgent> getAgents() {
    	return this.agents;
    }
	
}

