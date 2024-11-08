package cpsc457a3q4;

import java.util.ArrayList;

public class TokenRingAgent extends Thread {
	public int processorId; // Logical ID for the processor
	public TokenRingAgent ringPredecessor; // Logical ID of predecessor in the ring
	public TokenRingAgent ringSuccessor; // Logical ID of successor in the ring
	private boolean active; // Determines if the token ring is active
	private Token token; // The token currently held
	
	private ArrayList<Token> validTokens;

	// Constructor to initialize TokenRingAgent
	public TokenRingAgent(int processorId, int ringPredecessor, int ringSuccessor, boolean active, ArrayList<Token> validTokens) {
		this.processorId = processorId;
		this.active = active;
		this.validTokens = validTokens; 
	}

	// Method to receive the token from the predecessor
	public synchronized String receiveToken() {
		if(this.token == null) {
			return "";
		}

	    String tokenId = token.getId(); // Get the token ID
	    return tokenId;
	}

	// Method to receive a new token (sets the token directly)
	public synchronized void setToken(Token t) {
		this.token = t;
	}
	
    @Override
    public void run() {
    	while(true) {
    	}
    }
}
