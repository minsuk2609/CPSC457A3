package cpsc457q4;

import java.util.ArrayList;

public class TokenRing {

    private ArrayList<TokenRingAgent> ringAgents;
    private Token token;   // The token to be passed around the ring

    public TokenRing(ArrayList<TokenRingAgent> ringAgents) {
        this.ringAgents = ringAgents;
        
        // Step 2: Link the agents in a ring (circular linked structure)
        for (int i = 0; i < ringAgents.size(); i++) {
            TokenRingAgent current = ringAgents.get(i);
            TokenRingAgent next = ringAgents.get((i + 1) % ringAgents.size());  // Next agent (circular)
            TokenRingAgent previous = ringAgents.get((i - 1 + ringAgents.size()) % ringAgents.size());  // Previous agent (circular)

            // Set the next and previous references
            current.setRingSuccessor(next);
            current.setRingPredecessor(previous);
        }
    }

    // Method to start the token ring process
    public void startTokenRing() {
        // Initially give the token to the first agent
    	ringAgents.get(0).setToken(this.token);
    	
    }
    
    public TokenRingAgent getAgent(int agentId) {
    	return ringAgents.get(agentId);
    }
    
    public void setToken(String token) {
    	this.token = new Token(token);
    }
    
    public Token getToken() {
    	return this.token;
    }
}
