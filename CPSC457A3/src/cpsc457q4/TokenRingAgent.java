package cpsc457q4;

public class TokenRingAgent extends Thread {
	private int processorId; // Logical ID for the processor
	private TokenRingAgent ringPredecessor; // Logical ID of predecessor in the ring
	private TokenRingAgent ringSuccessor; // Logical ID of successor in the ring
	private boolean active; // Determines if the token ring is active
	private Token token; // The token currently held

	// Constructor to initialize TokenRingAgent
	public TokenRingAgent(int processorId, int ringPredecessor, int ringSuccessor, boolean active) {
		this.processorId = processorId;
		this.active = active;
	}

	// Method to receive the token from the predecessor
	public synchronized String receiveToken() {
		if(this.token == null) {
			return "";
		}
	    String tokenId = token.getId(); // Get the token ID
	    return tokenId;
	}
	
	public synchronized Token getToken() {
		return this.token;
	}

	// Method to receive a new token (sets the token directly)
	public synchronized void setToken(Token t) {
		this.token = t;
	}
	
    public TokenRingAgent getRingSuccessor() {
		return ringSuccessor;
	}

	public void setRingSuccessor(TokenRingAgent ringSuccessor) {
		this.ringSuccessor = ringSuccessor;
	}

	public TokenRingAgent getRingPredecessor() {
		return ringPredecessor;
	}

	public void setRingPredecessor(TokenRingAgent ringPredecessor) {
		this.ringPredecessor = ringPredecessor;
	}

	@Override
    public void run() {
    	while(true) {
    	}
    }
}
