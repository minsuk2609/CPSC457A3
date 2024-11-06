package cpsc457a3q3q4;

public class TokenRingAgent extends Thread {
	public int processorId; // Logical ID for the processor
	public TokenRingAgent ringPredecessor; // Logical ID of predecessor in the ring
	public TokenRingAgent ringSuccessor; // Logical ID of successor in the ring
	private boolean active; // Determines if the token ring is active
	private Token token; // The token currently held

	// Constructor to initialize TokenRingAgent
	public TokenRingAgent(int processorId, int ringPredecessor, int ringSuccessor, boolean active) {
		this.processorId = processorId;
		this.active = active;
	}

	// Method to receive the token from the predecessor
	public String receiveToken() {
		if (this.token == null) {
			return "test";
		}
	    String tokenId = token.getId(); // Get the token ID
	    this.token = null; // Consume the token
	    return tokenId;
	}

	// Method to receive a new token (sets the token directly)
	public synchronized void setToken(Token t) {
		this.token = t;
	}
}
